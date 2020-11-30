package technopark.andruxa.myapplication.network

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

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

    @POST("2.0/?method=auth.getMobileSession")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("api_key") apiKey: String,
        @Query("api_sig") apiSig: String
    ): Call<Response>?
}