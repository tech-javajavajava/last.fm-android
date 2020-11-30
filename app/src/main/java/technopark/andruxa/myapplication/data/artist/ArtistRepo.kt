package technopark.andruxa.myapplication.data.artist

import technopark.andruxa.myapplication.models.artist.Artist

interface ArtistRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Artist

    fun getByName(
        name: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Artist
}