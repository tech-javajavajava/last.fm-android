package technopark.andruxa.myapplication.network.album.retrofit2

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.models.album.AlbumSearchResponse
import technopark.andruxa.myapplication.models.album.XML.AlbumSearchResponseXML
import technopark.andruxa.myapplication.network.album.AlbumApi

class AlbumRetrofit: AlbumApi {

    private lateinit var api: RetrofitAlbum
    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder().exceptionOnUnreadXml(false).build()
                ))
            .baseUrl(
                HttpUrl.Builder().scheme("https")
                    .host("ws.audioscrobbler.com")
                    .build()
            )
            .client(OkHttpClient().newBuilder().build())
            .build()

        api = retrofit.create(RetrofitAlbum::class.java)
    }


    override fun getByName(name: String): AlbumSearchResponse {
        return api.innerGetByName(name).execute().body()!!
    }

    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    override fun getByName(name: String, limit: Int): AlbumSearchResponse {
        return api.innerGetByName(name, limit).execute().body()!!
    }
}

interface RetrofitAlbum {
    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun innerGetByName(@Query("album") album: String): Call<AlbumSearchResponseXML>

    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun innerGetByName(@Query("album") album: String, @Query("limit") limit: Int): Call<AlbumSearchResponseXML>
}
