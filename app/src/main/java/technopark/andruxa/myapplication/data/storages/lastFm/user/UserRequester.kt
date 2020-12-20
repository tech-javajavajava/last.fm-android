package technopark.andruxa.myapplication.data.storages.lastFm.user

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore

interface UserRequester {
    @GET("/2.0/?method=user.getinfo")
    fun getInfo(
        @Query("sk") sessionKey: String,
        @Query("api_sig") apiSig: String,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<UserInfoXML>

    @GET("/2.0/?method=user.getlovedtracks")
    fun getLoved(
        @Query("user") name: String,
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<UserLovedXML>

    @GET("/2.0/?method=user.getrecenttracks")
    fun getRecent(
        @Query("user") name: String,
        @Query("limit") limit: Int = 50,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey,
    ): Call<UserRecentXML>
}