package technopark.andruxa.myapplication.data.track

import technopark.andruxa.myapplication.models.track.Track

interface TrackRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean? = null,
        userName: String? = null,
    ): Track

    fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean? = null,
        userName: String? = null,
    ): Track

    fun save(vararg tracks: Track): List<Track>

    fun deleteByMbid(mbid: String): Track
    fun deleteByNameNArtist(name: String, artistName: String): Track

    fun searchByName(name: String, artistName: String?, limit: Int = 50, page: Int = 1): List<Track>
    fun getTop(limit: Int = 50, page: Int = 1): List<Track>
    fun setTop(vararg tracks: Track): List<Track>
}