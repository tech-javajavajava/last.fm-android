package technopark.andruxa.myapplication.data.track

import technopark.andruxa.myapplication.models.track.Track

interface TrackRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
    ): Track

    fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?,
    ): Track
}