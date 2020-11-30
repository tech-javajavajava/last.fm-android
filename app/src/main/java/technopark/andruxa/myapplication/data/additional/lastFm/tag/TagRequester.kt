package technopark.andruxa.myapplication.data.additional.lastFm.tag

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.additional.lastFm.Requester

interface TagRequester {
    @GET("/2.0/?method=tag.getinfo")
    fun getInfoByName(
        @Query("tag") tag: String,
        @Query("lang") lang: String = Requester.getInstance().lang,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TagInfoXML>

    @GET("/2.0/?method=chart.gettoptags")
    fun getTop(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TopTagsXML>
}