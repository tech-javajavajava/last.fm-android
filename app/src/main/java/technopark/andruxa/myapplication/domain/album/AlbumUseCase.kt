package technopark.andruxa.myapplication.domain.album

import technopark.andruxa.myapplication.data.album.AlbumRepo
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.domain.AlbumUseCase
import technopark.andruxa.myapplication.models.album.Album

class AlbumUseCase(val albumRepo: AlbumRepo) : AlbumUseCase {
    override fun Get(name: String, artist: String): Album {
        val Album = albumRepo.getByNameNActor(name, artist, null, null, null)
        return Album
    }

    override fun Search(name: String, limit: Int): Array<Album>? {
        TODO("Not yet implemented")
    }
}