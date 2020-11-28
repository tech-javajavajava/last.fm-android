package technopark.andruxa.myapplication.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.net.URL

class Api {
    var userApi: UserApi? = null
    var trackApi: TrackApi? = null
    var artistApi: ArtistApi? = null
    var albumApi: AlbumApi? = null
    var tagApi: TagApi? = null
    private var client: OkHttpClient = OkHttpClient().newBuilder().build()

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(
                TikXml.Builder().exceptionOnUnreadXml(false).build()
            ))
            .baseUrl(
                HttpUrl.Builder().scheme("https")
                    .host("ws.audioscrobbler.com")
                    .build()
            )
            .client(client)
            .build()
        userApi = retrofit.create(UserApi::class.java)
        trackApi = retrofit.create(TrackApi::class.java)
        artistApi = retrofit.create(ArtistApi::class.java)
        albumApi = retrofit.create(AlbumApi::class.java)
        tagApi = retrofit.create(TagApi::class.java)
    }

    @Xml
    class Image {
        //        @Attribute
//        var size: String? = null
        @TextContent
        var url: String? = null
    }

    fun getImageByUrl(url: String): Bitmap? {
        try {
            URL(url)
        } catch (t: Throwable) {
            return null
        }
        return BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
    }
}