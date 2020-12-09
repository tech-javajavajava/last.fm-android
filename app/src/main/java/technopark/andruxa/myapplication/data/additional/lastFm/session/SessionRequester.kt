package technopark.andruxa.myapplication.data.additional.lastFm.session

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import technopark.andruxa.myapplication.Utils
import technopark.andruxa.myapplication.data.additional.lastFm.Requester

interface SessionRequester {
    @POST("2.0")
    fun login(@Body body: SessionAuthBody): Call<SessionResponseXML>
}