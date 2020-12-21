package technopark.andruxa.myapplication.data.storages.lastFm.session

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

interface SessionRequester {
    @POST("2.0?method=auth.getMobileSession")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("api_sig") api_sig: String,
        @Query("api_key") api_key: String = LastFmStore.instance.apiKey,
    ): Call<SessionResponseXML>
}