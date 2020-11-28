package technopark.andruxa.myapplication.data.track

import technopark.andruxa.myapplication.models.album.Album

interface TrackRepo {
    fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
    ): Album

    fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?,
    ): Album
}