package technopark.andruxa.myapplication.domain

import technopark.andruxa.myapplication.data.album.AlbumRepo
import technopark.andruxa.myapplication.models.album.Album

interface AlbumUseCase {
    fun Get(name: String, artist: String): Album
}