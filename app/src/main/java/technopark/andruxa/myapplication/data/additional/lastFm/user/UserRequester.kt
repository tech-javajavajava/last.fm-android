package technopark.andruxa.myapplication.data.additional.lastFm.user

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.data.additional.lastFm.Requester

interface UserRequester {
    @GET("/2.0/?method=user.getinfo")
    fun getInfo(
        @Query("user") name: String? = null,
        @Query("api_key") apiKey: String = Requester.getInstance().apiKey,
        @Query("sk") sessionKey: String = Requester.getInstance().sessionKey,
        @Query("api_sig") apiSig: String = Requester.getInstance().apiSig
    ): Call<UserInfoXML>
}