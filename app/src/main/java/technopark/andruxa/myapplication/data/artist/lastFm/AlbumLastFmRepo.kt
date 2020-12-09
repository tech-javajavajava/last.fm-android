package technopark.andruxa.myapplication.data.artist.lastFm

import technopark.andruxa.myapplication.data.additional.lastFm.Requester
import technopark.andruxa.myapplication.data.additional.lastFm.artist.ArtistInfoXML
import technopark.andruxa.myapplication.data.artist.ArtistRepo
import technopark.andruxa.myapplication.models.artist.Artist

class ArtistLastFmRepo: ArtistRepo {
    override fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Artist {
        if (mbid == "") return ArtistInfoXML().apply { setEmptyMbid() }
        val autocorrect = if (autoCorrect == null || autoCorrect == false) 0 else 1
        val response = Requester
            .getInstance()
            .artistApi
            .getInfoByMbid(mbid, autocorrect, userName)
            .execute()

        if (response.isSuccessful) {
//            return response.body()!!.initImages()
            return response.body()!!
        }

        return ArtistInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun searchByName(name: String, limit: Int, page: Int): List<Artist> {
        val response = Requester
            .getInstance()
            .artistApi
            .searchByName(name, page, limit)
            .execute()

        if (response.isSuccessful) {
//            return response.body()!!.artists.map { it.initImages() }
            return response.body()!!.artists
        }

        return emptyList()
    }

    override fun getTop(limit: Int, page: Int): List<Artist> {
        val response = Requester
            .getInstance()
            .artistApi
            .getTop(page, limit)
            .execute()

        if (response.isSuccessful) {
//            return response.body()!!.artists.map { it.initImages() }
            return response.body()!!.artists
        }

        return emptyList()
    }

    override fun delete(vararg artists: Artist): List<Artist> {
        return emptyList()
    }

    override fun save(vararg artists: Artist): List<Artist> {
        return emptyList()
    }

    override fun setTop(vararg artists: Artist): List<Artist> {
        return emptyList()
    }
}