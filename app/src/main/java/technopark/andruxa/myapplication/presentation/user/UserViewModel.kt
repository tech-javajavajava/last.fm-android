package technopark.andruxa.myapplication.presentation.user

import android.app.Application
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.util.*


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private var lastLoginData = LoginData("", "")
    private val loginState = MediatorLiveData<LoginState>()

    init {
        loginState.value = LoginState.NONE
    }

    fun getProgress(): LiveData<LoginState> {
        return loginState
    }

    fun login(login: String, password: String) {
        val last = lastLoginData
        val loginData = LoginData(login, password)
        lastLoginData = loginData
        if (!loginData.isValid) {
            loginState.postValue(LoginState.ERROR)
        } else if (loginState.value != LoginState.IN_PROGRESS && last != loginData) {
            requestLogin(loginData)
        }
    }

    private fun requestLogin(loginData: LoginData) {
        Log.d("lol", "request")
        loginState.postValue(LoginState.IN_PROGRESS)
        val progressLiveData: LiveData<UserRepository.AuthProgress> =
            UserRepository.getInstance(getApplication()).login(loginData.login, loginData.password)!!
        loginState.addSource(progressLiveData) { authProgress ->
            Log.d("lol", "callback")
            if (authProgress === UserRepository.AuthProgress.SUCCESS) {
                Log.d("lol", "success")
                loginState.postValue(LoginState.SUCCESS)
                loginState.removeSource(progressLiveData)
            } else if (authProgress === UserRepository.AuthProgress.FAILED) {
                loginState.postValue(LoginState.FAILED)
                loginState.removeSource(progressLiveData)
            }
        }
    }


    enum class LoginState {
        NONE, ERROR, IN_PROGRESS, SUCCESS, FAILED
    }

    class LoginData(val login: String, val password: String) {
        val isValid: Boolean
            get() = !TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || javaClass != other.javaClass) return false
            val loginData = other as LoginData
            return Objects.equals(login, loginData.login) &&
                    Objects.equals(password, loginData.password)
        }

        override fun hashCode(): Int {
            return Objects.hash(login, password)
        }
    }
}