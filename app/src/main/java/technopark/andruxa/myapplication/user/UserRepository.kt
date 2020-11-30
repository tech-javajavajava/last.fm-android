package technopark.andruxa.myapplication.user

import android.content.Context
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.Constants
import technopark.andruxa.myapplication.Utils
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.network.UserApi

class UserRepository(private var api: Api?) {

    companion object {
        fun getInstance(context: Context?): UserRepository {
            return ApplicationModified.from(context).userRepository!!
        }
    }

    private var currentUser: String? = null
    private var authProgress: MutableLiveData<AuthProgress>? = null

    fun login(login: String, password: String): LiveData<AuthProgress>? {
        Log.d("lol", "login")
        if (TextUtils.equals(login, currentUser) && authProgress!!.value == AuthProgress.IN_PROGRESS) {
            return authProgress
        } else if (!TextUtils.equals(login, currentUser) && authProgress != null) {
            authProgress!!.postValue(AuthProgress.FAILED)
        }
        currentUser = login
        authProgress = MutableLiveData(AuthProgress.IN_PROGRESS)
        login(authProgress!!, login, password)
        return authProgress
    }

    private fun login(progress: MutableLiveData<AuthProgress>, login: String, password: String) {
        Log.d("lol", "login 2")
        val api: UserApi? = api?.userApi
        api?.login(
            login,
            password,
            Constants.lastFmApiKey,
            Utils.md5("api_key" + Constants.lastFmApiKey + "methodauth.getMobileSessionpassword" + password + "username" + login + Constants.lastFmApiSecret)
        )?.enqueue(object : Callback<UserApi.Response> {
            override fun onResponse(
                call: Call<UserApi.Response>?,
                response: Response<UserApi.Response>
            ) {
                Log.d("lol", "response")
                response.body()?.let {
                    it.session?.let {
                        if (response.isSuccessful) {
//                            sessionKey = it.key
                            progress.postValue(AuthProgress.SUCCESS)
                            return
                        }
                    }
                }
                progress.postValue(AuthProgress.FAILED)
            }

            override fun onFailure(call: Call<UserApi.Response>?, t: Throwable?) {
                progress.postValue(AuthProgress.FAILED)
            }
        })
    }

    enum class AuthProgress {
        IN_PROGRESS, SUCCESS, FAILED
    }
}