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

    fun save(track: Track): Track

    fun deleteByMbid(mbid: String): Track
    fun deleteByNameNArtist(name: String, artistName: String): Track
}