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
        var api_key: String = "3b8a89498b5ab698a8966a966e97c5a1"
        var api_sig: String
        var format: String = "json"
        init {
            api_sig = Utils.md5("api_key" + api_key + "method" + method + "password" + password + "username" + username + "a04db502e40a1249a02833ea3953071f")
            Log.d("lol", api_sig)
        }
    }

    @POST("2.0")
    fun login(@Body body: AuthBody): Call<Response>?
}