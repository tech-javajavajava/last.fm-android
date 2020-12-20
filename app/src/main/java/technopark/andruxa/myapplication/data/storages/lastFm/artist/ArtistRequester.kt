package technopark.andruxa.myapplication.data.storages.lastFm.artist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

interface ArtistRequester {
    @GET("/2.0/?method=artist.getinfo")
    fun getInfoByName(
        @Query("artist") artist: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<ArtistInfoXML>

    @GET("/2.0/?method=artist.getinfo")
    fun getByMbid(
        @Query("mbid") mbid: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<ArtistInfoXML>

    @GET("/2.0/?method=artist.search")
    fun searchByName(
        @Query("artist") artist: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<ArtistSearchXML>

    @GET(" /2.0/?method=chart.gettopartists")
    fun getTop(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<ArtistsTopXML>
}