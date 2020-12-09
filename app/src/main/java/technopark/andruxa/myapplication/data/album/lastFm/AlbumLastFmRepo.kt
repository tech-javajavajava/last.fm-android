package technopark.andruxa.myapplication.data.album.lastFm

import retrofit2.Response
import technopark.andruxa.myapplication.data.additional.lastFm.Requester
import technopark.andruxa.myapplication.data.additional.lastFm.album.AlbumInfoXML
import technopark.andruxa.myapplication.data.album.AlbumRepo
import technopark.andruxa.myapplication.models.album.Album

class AlbumLastFmRepo: AlbumRepo {
    override fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album {
        if (mbid == "") return AlbumInfoXML().apply { setEmptyMbid() }
        val autocorrect = if (autoCorrect == null || autoCorrect == false) 0 else 1
        val response = Requester
            .getInstance()
            .albumApi
            .getInfoByMbid(mbid, autocorrect, userName)
            .execute()

        if (response.isSuccessful) {
            return response.body()!!
//            return response.body()!!.initImages()
        }

        return AlbumInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album {
        val autocorrect = if (autoCorrect == null || autoCorrect == false) 0 else 1
        val response = Requester
            .getInstance()
            .albumApi
            .getInfoByNameNArtist(name, artistName, autocorrect, userName)
            .execute()

        if (response.isSuccessful) {
//            return response.body()!!.initImages()
            return response.body()!!
        }

        return AlbumInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun searchByName(name: String, limit: Int, page: Int): List<Album> {
        val response = Requester
            .getInstance()
            .albumApi
            .searchByName(name, page, limit)
            .execute()

        if (response.isSuccessful) {
//            return response.body()!!.albums.map { it.initImages() }
            return response.body()!!.albums
        }

        return emptyList()
    }

    override fun delete(vararg albums: Album): List<Album> {
        return emptyList()
    }

    override fun save(vararg albums: Album): List<Album> {
        return emptyList()
    }
}