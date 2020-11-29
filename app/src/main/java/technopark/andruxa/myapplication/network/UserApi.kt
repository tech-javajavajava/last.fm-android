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

    class AuthBody(val username: String, val password: String) {
        val method: String = "auth.getMobileSession"
        val api_key: String = "3b8a89498b5ab698a8966a966e97c5a1"
        val api_secret: String = "a04db502e40a1249a02833ea3953071f"
        val api_sig: String

        init {
            val concat_str: String =
                "api_key" + api_key + "method" + method + "password" + password + "username" + username + api_secret
            Log.d("lol", concat_str)
            api_sig = Utils.md5(concat_str)
            Log.d("lol", api_sig)
        }
    }

    @POST("2.0")
    fun login(@Body body: AuthBody): Call<Response>?
}