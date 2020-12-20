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
        @Query("api_key") apiKey: String = LastFmStore.instance.apiKey
    ): Call<UserInfoXML>
}