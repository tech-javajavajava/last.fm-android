package technopark.andruxa.myapplication.network

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api {
    var userApi: UserApi? = null
    var trackApi: TrackApi? = null
    var artistApi: ArtistApi? = null
    var albumApi: AlbumApi? = null
    private var client: OkHttpClient = OkHttpClient().newBuilder().build()

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
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
    }
}