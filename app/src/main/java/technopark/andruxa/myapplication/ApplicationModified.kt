package technopark.andruxa.myapplication

import android.app.Application
import android.content.Context
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.data.user.room.UserRoomRepo
import technopark.andruxa.myapplication.domain.user.UserUseCase
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.user.UserRepository

class ApplicationModified : Application() {
    var api: Api? = null
    var userRepository: UserRepository? = null
    var tracksRepository: TracksRepository? = null
    var artistsRepository: ArtistsRepository? = null
    var albumsRepository: AlbumsRepository? = null
    var imagesRepository: ImagesRepository? = null
    var tagsRepository: TagsRepository? = null
    var userRoomRepo: UserRoomRepo? = null
    var userUseCase: UserUseCase? = null

    override fun onCreate() {
        super.onCreate()
        context = this
        api = Api()
        userRoomRepo = UserRoomRepo()
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
            return context?.getApplicationContext() as ApplicationModified
        }
    }
}