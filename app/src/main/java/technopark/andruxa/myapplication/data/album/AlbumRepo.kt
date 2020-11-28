package technopark.andruxa.myapplication.data.album

import technopark.andruxa.myapplication.models.Album

interface AlbumRepo {
    fun getAlbumByMbid(
        mbdi: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

    fun getAlbumByName(
        name: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album // probably not working without actor name

    fun getAlbumByNameNActor(
        name: String,
        actorName: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

}