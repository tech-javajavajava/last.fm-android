package technopark.andruxa.myapplication.data.album

import technopark.andruxa.myapplication.models.Album

interface AlbumRepo {
    fun getByMbid(
        mbdi: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

    fun getByName(
        name: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album // probably not working without actor name

    fun getByNameNActor(
        name: String,
        actorName: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album
}