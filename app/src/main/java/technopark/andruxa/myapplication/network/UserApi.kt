package technopark.andruxa.myapplication.network

import android.util.Log
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import technopark.andruxa.myapplication.Constants
import technopark.andruxa.myapplication.Utils

interface UserApi {
    @Xml(name = "lfm")
    class Response {
        @Element
        var session: Session? = null
    }

    @Xml(name = "session")
    class Session {
//        @PropertyElement
//        var name: String? = null
        @PropertyElement
        var key: String? = null
//        @PropertyElement
//        var subscriber: Int? = null
    }

    class AuthBody(val username: String, val password: String) {
        val method: String = "auth.getMobileSession"
        val api_key: String = Constants.lastFmApiKey
        val api_sig: String

        init {
            val concatStr: String =
                "api_key" + api_key + "method" + method + "password" + password + "username" + username + Constants.lastFmApiSecret
            Log.d("lol", concatStr)
            api_sig = Utils.md5(concatStr)
            Log.d("lol", api_sig)
        }
    }

    @POST("2.0")
    fun login(@Body body: AuthBody): Call<Response>?
}