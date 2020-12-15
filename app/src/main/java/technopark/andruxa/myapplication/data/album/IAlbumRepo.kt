package technopark.andruxa.myapplication.data.album

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.album.Album

interface IAlbumRepo {
    val albumById: SDataI<Album>
    val albumByNameNArtist: SDataI<Album>
    val albumSearch: SDataI<List<Album>>

    fun getById(id: String): SDataI<Album>

    fun getByNameNArtist(
        name: String,
        artistName: String,
        userName: String? = null,
    ): SDataI<Album>

    fun searchByName(name: String, limit: Int = 50, page: Int = 1): SDataI<List<Album>>
}
