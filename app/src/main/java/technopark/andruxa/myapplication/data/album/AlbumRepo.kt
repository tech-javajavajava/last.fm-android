package technopark.andruxa.myapplication.data.album

import technopark.andruxa.myapplication.models.Album

interface AlbumRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

    fun getByNameNActor(
        name: String,
        actorName: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album
}