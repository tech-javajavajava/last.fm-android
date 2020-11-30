package technopark.andruxa.myapplication.data.track.room

import technopark.andruxa.myapplication.data.additional.room.track.TrackRoomDao
import technopark.andruxa.myapplication.data.additional.room.track.TrackRoomEntity
import technopark.andruxa.myapplication.data.additional.room.track.fromTrack
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

    override fun save(vararg tracks: Track): List<Track> {
        @Suppress("UNCHECKED_CAST")
        TrackRoomDao.get().save(*tracks as Array<out TrackRoomEntity>)
        return listOf(*tracks)
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

    override fun searchByName(
        name: String,
        artistName: String?,
        limit: Int,
        page: Int,
    ): List<Track> {
        val offset = limit * (page - 1)
        if (artistName == null) {
            return TrackRoomDao.get().searchByName(name, limit, offset)
        }

        return TrackRoomDao.get().searchByNameNArtist(name, artistName, limit, offset)
    }

    override fun getTop(limit: Int, page: Int): List<Track> {
        return TrackRoomDao.get().getTop(limit, limit * (page - 1))
    }

    override fun setTop(vararg tracks: Track): List<Track> {
        val roomTracks: List<TrackRoomEntity> = tracks.map { fromTrack(it).apply { isTop = true } }
        TrackRoomDao.get().save(*roomTracks.toTypedArray())
        return roomTracks
    }
}