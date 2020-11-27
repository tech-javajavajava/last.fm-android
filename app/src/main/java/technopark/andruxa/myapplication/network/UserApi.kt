package technopark.andruxa.myapplication.network

import android.util.Log
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import technopark.andruxa.myapplication.Utils

interface UserApi {
    class Response {
        var success: Boolean? = null
    }

    class AuthBody(var username: String, var password: String) {
        var method: String = "auth.getMobileSession"
        var api_key: String? = System.getenv("last_fm_api_key") ?: ""
        var api_sig: String
        init {
            api_sig = Utils.md5("api_key" + api_key + "method" + method + "password" + password + "username" + username + System.getenv("last_fm_api_secret"))
            Log.d("lol", api_sig)
        }
    }

    @POST("2.0")
    fun login(@Body body: AuthBody): Call<Response>?
}