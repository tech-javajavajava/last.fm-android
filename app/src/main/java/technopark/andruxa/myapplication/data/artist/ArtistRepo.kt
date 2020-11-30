package technopark.andruxa.myapplication.data.artist

import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

interface ArtistRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Artist

    fun searchByName(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): List<Artist>

    fun delete(vararg artists: Artist): List<Artist>
    fun save(vararg artists: Artist): List<Artist>
    fun getTop(limit: Int = 50, page: Int = 1): List<Artist>
    fun setTop(vararg artists: Artist): List<Artist>
}