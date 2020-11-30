package technopark.andruxa.myapplication.data.additional.lastFm.artist

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.additional.lastFm.Requester

interface ArtistRequester {
    @GET("/2.0/?method=artist.getinfo")
    fun getInfoByNameNArtist(
        @Query("album") album: String,
        @Query("artist") artist: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = Requester.getInstance().lang,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<ArtistInfoXML>

    @GET("/2.0/?method=artist.getinfo")
    fun getInfoByMbid(
        @Query("mbid") mbid: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = Requester.getInstance().lang,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<ArtistInfoXML>

    @GET("/2.0/?method=artist.search")
    fun searchByName(
        @Query("album") album: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<ArtistSearchXML>

    @GET(" /2.0/?method=chart.gettopartists")
    fun getTop(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
    ): Call<TopArtistsXML>
}