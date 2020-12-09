package technopark.andruxa.myapplication

import android.app.Application
import android.content.Context
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.presentation.user.UserRepository

class ApplicationModified : Application() {
    var api: Api? = null
    var userRepository: UserRepository? = null
    var tracksRepository: TracksRepository? = null
    var artistsRepository: ArtistsRepository? = null
    var albumsRepository: AlbumsRepository? = null
    var imagesRepository: ImagesRepository? = null
    var tagsRepository: TagsRepository? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        api = Api()
        userRepository = UserRepository(api)
        tracksRepository = TracksRepository(api)
        artistsRepository = ArtistsRepository(api)
        albumsRepository = AlbumsRepository(api)
        imagesRepository = ImagesRepository(api)
        tagsRepository = TagsRepository(api)
    }

    companion object {
        var context: Context? = null
        fun from(context: Context?): ApplicationModified {
            return context?.applicationContext as ApplicationModified
        }
    }
}