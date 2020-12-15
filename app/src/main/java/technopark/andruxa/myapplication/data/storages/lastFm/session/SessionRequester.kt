package technopark.andruxa.myapplication.data.storages.lastFm.session

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SessionRequester {
    @POST("2.0")
    fun login(@Body body: SessionAuthBody): Call<SessionResponseXML>
}