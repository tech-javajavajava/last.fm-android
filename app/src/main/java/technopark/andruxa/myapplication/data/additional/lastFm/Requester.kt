package technopark.andruxa.myapplication.data.additional.lastFm

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import technopark.andruxa.myapplication.data.additional.lastFm.album.AlbumRequester

class Requester {
    private val client: OkHttpClient = OkHttpClient().newBuilder().build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            TikXmlConverterFactory.create(
            TikXml.Builder().exceptionOnUnreadXml(false).build()
        ))
        .baseUrl(
            HttpUrl.Builder()
                .scheme("https")
                .host("ws.audioscrobbler.com")
                .build()
        )
        .client(client)
        .build()
    val apiKey = "3b8a89498b5ab698a8966a966e97c5a1"
    val lang = "RU"

    val albumApi = retrofit.create(AlbumRequester::class.java)

    companion object {
        var requester: Requester? = null
        fun getInstance(): Requester {
            if (requester == null) {
                requester = Requester()
            }

            return requester!!
        }
    }
}