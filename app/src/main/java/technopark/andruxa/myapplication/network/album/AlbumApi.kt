package technopark.andruxa.myapplication.network.album

import technopark.andruxa.myapplication.models.album.AlbumSearchResponse

interface AlbumApi {
    fun getByName(name: String): AlbumSearchResponse
    fun getByName(name: String, limit: Int): AlbumSearchResponse
}
