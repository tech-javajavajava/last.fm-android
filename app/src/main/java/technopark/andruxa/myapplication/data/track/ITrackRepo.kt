package technopark.andruxa.myapplication.data.track

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.track.Track

interface ITrackRepo {

    val trackById: SDataI<Track>

    val trackByNameNArtist: SDataI<Track>

    val trackSearched: SDataI<List<Track>>

    val trackTop: SDataI<List<Track>>

    val trackSimilar: SDataI<List<Track>>

    fun getById(
        id: String,
        userName: String? = null,
    ): SDataI<Track>

    fun getByNameNArtist(
        name: String,
        artistName: String,
        userName: String? = null,
    ): SDataI<Track>

    fun getSimilar(
        name: String,
        artistName: String,
    ): SDataI<List<Track>>

    fun searchByName(name: String, artistName: String?, limit: Int = 50, page: Int = 1): SDataI<List<Track>>
    fun getTop(limit: Int = 50, page: Int = 1): SDataI<List<Track>>
}