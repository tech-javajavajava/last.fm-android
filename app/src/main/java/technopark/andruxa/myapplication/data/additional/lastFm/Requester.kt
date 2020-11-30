package technopark.andruxa.myapplication.data.additional.lastFm

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import technopark.andruxa.myapplication.data.additional.lastFm.album.AlbumRequester
import technopark.andruxa.myapplication.data.additional.lastFm.artist.ArtistRequester
import technopark.andruxa.myapplication.data.additional.lastFm.session.SessionAuthBody
import technopark.andruxa.myapplication.data.additional.lastFm.session.SessionRequester
import technopark.andruxa.myapplication.data.additional.lastFm.tag.TagRequester
import technopark.andruxa.myapplication.data.additional.lastFm.track.TrackRequester

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

    val albumApi: AlbumRequester = retrofit.create(AlbumRequester::class.java)
    val artistApi: ArtistRequester = retrofit.create(ArtistRequester::class.java)
    val tagsApi: TagRequester = retrofit.create(TagRequester::class.java)
    val trackApi: TrackRequester = retrofit.create(TrackRequester::class.java)

    private val sessionApi: SessionRequester = retrofit.create(SessionRequester::class.java)
    private var sessionKey: String = ""
    private var apiSig: String = ""


    fun login(username: String, password: String): Boolean {
        val response = sessionApi.login(SessionAuthBody(username, password)).execute()
        if (response.isSuccessful) {
            sessionKey = response.body()?.sessionKey ?: ""
            apiSig = response.body()?.apiSig.toString()
            return true
        }

        return false
    }

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