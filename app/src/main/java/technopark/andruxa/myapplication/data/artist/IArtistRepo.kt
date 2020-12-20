package technopark.andruxa.myapplication.data.artist

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

interface IArtistRepo {

    val artistById: SDataI<Artist>

    val artistByName: SDataI<Artist>

    val artistSearch: SDataI<List<Artist>>

    val artistTop: SDataI<List<Artist>>

    val artistTopAlbums: SDataI<List<Album>>

    val artistTopTracks: SDataI<List<Track>>

    val artistTopTags: SDataI<List<Tag>>

    fun getById(
        id: String,
        userName: String?,
    ): SDataI<Artist>

    fun getByName(
        name: String,
        userName: String?,
    ): SDataI<Artist>

    fun searchByName(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): SDataI<List<Artist>>

    fun getTop(limit: Int = 50, page: Int = 1): SDataI<List<Artist>>

    fun getTopAlbums(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): SDataI<List<Album>>

    fun getTopTags(
        name: String,
    ): SDataI<List<Tag>>

    fun getTopTracks(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): SDataI<List<Track>>
}