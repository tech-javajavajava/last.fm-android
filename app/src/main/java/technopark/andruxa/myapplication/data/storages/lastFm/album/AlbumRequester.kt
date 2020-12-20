package technopark.andruxa.myapplication.data.storages.lastFm.album

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

interface AlbumRequester {
    @GET("/2.0/?method=album.getinfo")
    fun getByNameNArtist(
        @Query("album") album: String,
        @Query("artist") artist: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<AlbumInfoXML>

    @GET("/2.0/?method=album.getinfo")
    fun getByMbid(
        @Query("mbid") mbid: String,
        @Query("autocorrect") autocorrect: Int? = null,
        @Query("username") userName: String? = null,
        @Query("lang") lang: String = LastFmStore.instance.lang,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<AlbumInfoXML>

    @GET("/2.0/?method=album.search")
    fun searchByName(
        @Query("album") album: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<AlbumSearchXML>
}