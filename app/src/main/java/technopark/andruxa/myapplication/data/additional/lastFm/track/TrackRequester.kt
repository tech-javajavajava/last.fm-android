package technopark.andruxa.myapplication.data.additional.lastFm.track

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.additional.lastFm.Requester

interface TrackRequester {
    @GET("/2.0/?method=track.getInfo")
    fun getInfoByNameNArtist(
        @Query("album") album: String,
        @Query("artist") artist: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = Requester.getInstance().lang,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TrackInfoXML>

    @GET("/2.0/?method=track.getInfo")
    fun getInfoByMbid(
        @Query("mbid") mbid: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = Requester.getInstance().lang,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TrackInfoXML>

    @GET("/2.0/?method=track.search")
    fun searchByName(
        @Query("track") name: String,
        @Query("artist") artistName: String? = null,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TrackSearchXML>

    @GET("/2.0/?method=chart.gettoptracks")
    fun getTop(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TopTracksXML>
}