package technopark.andruxa.myapplication.data.album

import technopark.andruxa.myapplication.models.album.Album

interface AlbumRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

    fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

    fun searchByName(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): List<Album>

    fun delete(vararg albums: Album): List<Album>
    fun save(vararg albums: Album): List<Album>
}