package technopark.andruxa.myapplication.data.storages.lastFm.tag

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

interface TagRequester {
    @GET("/2.0/?method=tag.getinfo")
    fun getInfoByName(
        @Query("tag") tag: String,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TagInfoXML>

    @GET("/2.0/?method=chart.gettoptags")
    fun getTop(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TagTopXML>
}