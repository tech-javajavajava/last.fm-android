package technopark.andruxa.myapplication.data.album

import androidx.lifecycle.LiveData
import technopark.andruxa.myapplication.data.IRepo
import technopark.andruxa.myapplication.models.album.Album

interface IAlbumRepo: IRepo {

    val album: LiveData<Album>

    val albums: LiveData<List<Album>>

    fun getById(id: String): LiveData<Album>

    fun getByNameNArtist(
        name: String,
        artistName: String,
        userName: String? = null,
    ): LiveData<Album>

    fun searchByName(name: String, limit: Int = 50, page: Int = 1): LiveData<List<Album>>
}
