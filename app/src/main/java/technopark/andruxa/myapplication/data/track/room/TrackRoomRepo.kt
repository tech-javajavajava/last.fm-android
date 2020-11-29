package technopark.andruxa.myapplication.data.track.room

import technopark.andruxa.myapplication.data.additional.room.track.TrackRoomDao
import technopark.andruxa.myapplication.data.additional.room.track.TrackRoomEntity
import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.models.track.Track

class TrackRoomRepo: TrackRepo {
    override fun getByMbid(mbid: String, autoCorrect: Boolean?, userName: String?): Track {
       return TrackRoomDao.get().getByMbid(mbid)
    }

    override fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?
    ): Track {
        return TrackRoomDao.get().getByNameNArtist(name, artistName)
    }

    override fun save(track: Track): Track {
        TrackRoomDao.get().save(track as TrackRoomEntity)
        return track
    }

    override fun deleteByMbid(mbid: String): Track {
        val track = getByMbid(mbid) as TrackRoomEntity
        if (track.isBroken()) {
            return track.notFound() as Track
        }
        TrackRoomDao.get().delete(track)
        return track
    }

    override fun deleteByNameNArtist(name: String, artistName: String): Track {
        val track = getByNameNArtist(name, artistName) as TrackRoomEntity
        if (track.isBroken()) {
            return track.notFound() as Track
        }
        TrackRoomDao.get().delete(track)
        return track
    }
}