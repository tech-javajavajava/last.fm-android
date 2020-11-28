package technopark.andruxa.myapplication.data.artist

import technopark.andruxa.myapplication.models.Album

interface ArtistRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album

    fun getByName(
        name: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album
}