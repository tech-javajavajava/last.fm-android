package technopark.andruxa.myapplication.data.tag

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

interface ITagRepo {
    val tagByName: SDataI<Tag>

    val tagTop: SDataI<List<Tag>>

    val tagSimilar: SDataI<List<Tag>>

    val tagTopAlbums: SDataI<List<Album>>

    val tagTopArtists: SDataI<List<Artist>>

    val tagTopTags: SDataI<List<Tag>>

    val tagTopTracks: SDataI<List<Track>>

    fun getByName(name: String): SDataI<Tag>

    fun getTop(limit: Int = 50, page: Int = 1): SDataI<List<Tag>>

    fun getSimilar(name: String): SDataI<List<Tag>>

    fun getTopAlbums(tag: String, page: Int = 1, limit: Int = 50): SDataI<List<Album>>

    fun getTopArtists(tag: String, page: Int = 1, limit: Int = 50): SDataI<List<Artist>>

    fun getTopTags(): SDataI<List<Tag>>

    fun getTopTracks(name: String, page: Int = 1, limit: Int = 50): SDataI<List<Track>>
}