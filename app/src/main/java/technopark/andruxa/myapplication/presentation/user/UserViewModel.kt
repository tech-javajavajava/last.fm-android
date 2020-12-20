package technopark.andruxa.myapplication.presentation.user

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.session.SessionRepo
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.models.track.Track
import technopark.andruxa.myapplication.models.user.User


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val loginState = MediatorLiveData<LoginState>()
    private val profileState = MediatorLiveData<ProfileProgress>()

    init {
        loginState.value = LoginState.NONE
        profileState.value?.state = ProfileProgress.State.NONE
    }

    fun checkLogin(): Boolean {
        return SessionRepo.getInstance().isLogined.data == true
    }

    fun getLoginProgress(): LiveData<LoginState> {
        return loginState
    }

    fun login(login: String, password: String) {
        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)) {
            loginState.postValue(LoginState.ERROR)
        } else if (loginState.value != LoginState.IN_PROGRESS) {
            requestLogin(login, password)
        }
    }

    private fun requestLogin(login: String, password: String) {
        Log.d("auth", "request")
        loginState.postValue(LoginState.IN_PROGRESS)
        val progressLiveData = SessionRepo.getInstance().login(login, password)
        loginState.addSource(progressLiveData.state) { authProgress ->
            Log.d("auth", "callback")
            if (progressLiveData.isOk()) {
                Log.d("auth", "success")
                loginState.postValue(LoginState.SUCCESS)
                loginState.removeSource(progressLiveData.state)
            } else if (authProgress === SDataI.State.Err) {
                Log.d("auth", "failure")
                loginState.postValue(LoginState.FAILED)
                loginState.removeSource(progressLiveData.state)
            }
        }
    }

    fun getProfileProgress(): LiveData<ProfileProgress> {
        return profileState
    }

    fun getProfile() {
        if (profileState.value?.state == ProfileProgress.State.IN_PROGRESS) {
            return
        }
        Log.d("profile", "request")
        profileState.postValue(ProfileProgress(ProfileProgress.State.IN_PROGRESS))
        val userLiveData = UserRepo.getInstance().getCurrent() as SData<User>
        profileState.value?.username = null
        profileState.addSource(userLiveData.state) {
            Log.d("user", "callback")
            if (userLiveData.isOk()) {
                Log.d("user", "success")
                profileState.removeSource(userLiveData.state)
                userLiveData.data?.name?.let {
                    requestProfile(it)
                    profileState.value?.username = it
                }
                if (userLiveData.data == null || userLiveData.data?.name == null) {
                    profileState.postValue(ProfileProgress(ProfileProgress.State.FAILED))
                }
            } else if (it === SDataI.State.Err) {
                Log.d("user", "failure")
                profileState.postValue(ProfileProgress(ProfileProgress.State.FAILED))
                profileState.removeSource(userLiveData.state)
            }
        }
    }

    fun requestProfile(name: String) {
        val responsesAwaiting = 2
        var responsesRecieved = 0
        var responsesFailed = 0
        profileState.value?.loved = null
        profileState.value?.recent = null
        val lovedLiveData = UserRepo.getInstance().getLovedTracks(name, 3, 1)
        val recentLiveData = UserRepo.getInstance().getRecentTracks(name, 3, 1)
        profileState.addSource(lovedLiveData.state) {
            Log.d("loved", "callback")
            if (lovedLiveData.isOk()) {
                Log.d("loved", "success")
                profileState.value!!.loved = lovedLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    profileState.postValue(profileState.value!!.changeState(ProfileProgress.State.SUCCESS))
                }
                profileState.removeSource(lovedLiveData.state)
            } else if (it === SDataI.State.Err) {
                Log.d("loved", "failure")
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        profileState.postValue(ProfileProgress(ProfileProgress.State.FAILED))
                    } else {
                        profileState.postValue(profileState.value!!.changeState(ProfileProgress.State.SUCCESS))
                    }
                }
                profileState.removeSource(lovedLiveData.state)
            }
        }
        profileState.addSource(recentLiveData.state) {
            Log.d("recent", "callback")
            if (recentLiveData.isOk()) {
                Log.d("recent", "success")
                profileState.value!!.recent = recentLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    profileState.postValue(profileState.value!!.changeState(ProfileProgress.State.SUCCESS))
                }
                profileState.removeSource(recentLiveData.state)
            } else if (it === SDataI.State.Err) {
                Log.d("recent", "failure")
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        profileState.postValue(ProfileProgress(ProfileProgress.State.FAILED))
                    } else {
                        profileState.postValue(profileState.value!!.changeState(ProfileProgress.State.SUCCESS))
                    }
                }
                profileState.removeSource(recentLiveData.state)
            }
        }
    }

    enum class LoginState {
        NONE, ERROR, IN_PROGRESS, SUCCESS, FAILED
    }

    class ProfileProgress(var state: State) {
        enum class State {
            NONE, IN_PROGRESS, SUCCESS, FAILED
        }
        var username: String? = null
        var recent: List<Track>? = null
        var loved: List<Track>? = null
        fun changeState(state: State): ProfileProgress {
            this.state = state
            return this
        }
    }
}