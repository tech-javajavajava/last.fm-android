package technopark.andruxa.myapplication

import android.app.Application
import android.content.Context
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.user.UserRepository

class ApplicationModified : Application() {
    var api: Api? = null
    var userRepository: UserRepository? = null
    var tracksRepository: TracksRepository? = null
    var artistsRepository: ArtistsRepository? = null
    var albumsRepository: AlbumsRepository? = null

    override fun onCreate() {
        super.onCreate()
        api = Api()
        userRepository = UserRepository(api)
        tracksRepository = TracksRepository(api)
        artistsRepository = ArtistsRepository(api)
        albumsRepository = AlbumsRepository(api)
    }

    companion object {
        fun from(context: Context?): ApplicationModified {
            return context?.getApplicationContext() as ApplicationModified
        }
    }
}