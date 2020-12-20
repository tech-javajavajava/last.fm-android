package technopark.andruxa.myapplication.data.storages.lastFm.track

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

interface TrackRequester {
    @GET("/2.0/?method=track.getsimilar")
    fun getSimilar(
        @Query("track") name: String,
        @Query("artist") artistName: String,
        @Query("autocorrect") autocorrect: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TrackSimilarXML>

    @GET("/2.0/?method=track.getInfo")
    fun getByNameNArtist(
        @Query("track") track: String,
        @Query("artist") artist: String,
        @Query("autocorrect") autocorrect: Int? = 1,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TrackInfoXML>

    @GET("/2.0/?method=track.getInfo")
    fun getByMbid(
        @Query("mbid") mbid: String,
        @Query("autocorrect") autocorrect: Int? = 1,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TrackInfoXML>

    @GET("/2.0/?method=track.search")
    fun searchByName(
        @Query("track") name: String,
        @Query("artist") artistName: String? = null,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TrackSearchXML>

    @GET("/2.0/?method=chart.gettoptracks")
    fun getTop(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<TrackTopXML>
}