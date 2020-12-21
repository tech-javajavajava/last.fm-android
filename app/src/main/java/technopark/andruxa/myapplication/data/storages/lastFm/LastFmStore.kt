package technopark.andruxa.myapplication.data.storages.lastFm

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumRequester
import technopark.andruxa.myapplication.data.storages.lastFm.artist.ArtistRequester
import technopark.andruxa.myapplication.data.storages.lastFm.session.SessionRequester
import technopark.andruxa.myapplication.data.storages.lastFm.tag.TagRequester
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackRequester
import technopark.andruxa.myapplication.data.storages.lastFm.user.UserRequester

class LastFmStore {
    val client: OkHttpClient = OkHttpClient().newBuilder().build()
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
    val userApi: UserRequester = retrofit.create(UserRequester::class.java)
    val sessionApi: SessionRequester = retrofit.create(SessionRequester::class.java)

    companion object {
        var instance: LastFmStore = LastFmStore()
    }
}