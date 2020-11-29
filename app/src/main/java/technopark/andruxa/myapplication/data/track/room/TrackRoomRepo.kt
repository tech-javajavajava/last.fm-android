package technopark.andruxa.myapplication.data.track.room

import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.models.album.Album

class TrackRoomRepo: TrackRepo {
    override fun getByMbid(mbid: String, autoCorrect: Boolean?, userName: String?): Album {
        TODO("Not yet implemented")
    }

    override fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?
    ): Album {
        TODO("Not yet implemented")
    }
}