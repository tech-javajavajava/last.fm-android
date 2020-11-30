package technopark.andruxa.myapplication.data.track.lastFm

import technopark.andruxa.myapplication.data.additional.lastFm.Requester
import technopark.andruxa.myapplication.data.additional.lastFm.album.AlbumInfoXML
import technopark.andruxa.myapplication.data.additional.lastFm.track.TrackInfoXML
import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.models.track.Track

class TrackLastFmRepo: TrackRepo {
    override fun getByMbid(mbid: String, autoCorrect: Boolean?, userName: String?): Track {
        val autocorrect = if (autoCorrect == null || autoCorrect == false) 0 else 1

        val response = Requester
            .getInstance()
            .trackApi
            .getInfoByMbid(mbid, autocorrect, userName)
            .execute()

        if (response.isSuccessful) {
            return response.body()!!
        }

        return TrackInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?
    ): Track {
        val autocorrect = if (autoCorrect == null || autoCorrect == false) 0 else 1
        val response = Requester
            .getInstance()
            .trackApi
            .getInfoByNameNArtist(name, artistName, autocorrect, userName)
            .execute()

        if (response.isSuccessful) {
            return response.body()!!
        }

        return TrackInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun searchByName(
        name: String,
        artistName: String?,
        limit: Int,
        page: Int
    ): List<Track> {
        val response = Requester
            .getInstance()
            .trackApi
            .searchByName(name, artistName, page, limit)
            .execute()

        if (response.isSuccessful) {
            return response.body()!!.tracks
        }

        return emptyList()
    }

    override fun getTop(limit: Int, page: Int): List<Track> {
        val response = Requester
            .getInstance()
            .trackApi
            .getTop(page, limit)
            .execute()

        if (response.isSuccessful) {
            return response.body()!!.tracks
        }

        return emptyList()
    }

    override fun save(vararg tracks: Track): List<Track> {
        return emptyList()
    }

    override fun delete(vararg tracks: Track): List<Track> {
        return emptyList()
    }

    override fun deleteByMbid(mbid: String): Track {
        return TrackInfoXML().apply { setNotPermitted() }
    }

    override fun deleteByNameNArtist(name: String, artistName: String): Track {
        return TrackInfoXML().apply { setNotPermitted() }
    }

    override fun setTop(vararg tracks: Track): List<Track> {
        return emptyList()
    }
}