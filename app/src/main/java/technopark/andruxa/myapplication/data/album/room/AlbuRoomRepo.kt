package technopark.andruxa.myapplication.data.album.room

import technopark.andruxa.myapplication.data.additional.room.album.AlbumRoomDao
import technopark.andruxa.myapplication.data.additional.room.album.fromAlbum
import technopark.andruxa.myapplication.data.album.AlbumRepo
import technopark.andruxa.myapplication.models.album.Album

class AlbumRoomRepo: AlbumRepo {
    override fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album {
        return AlbumRoomDao.get().getByMbid(mbid)
    }

    override fun getByNameNArtist(
        name: String,
        artistName: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Album {
        return AlbumRoomDao.get().getByNameNArtist(name, artistName)
    }

    override fun searchByName(name: String, limit: Int, page: Int): List<Album> {
        return AlbumRoomDao.get().search(name, limit, limit * (page - 1))
    }

    override fun delete(vararg albums: Album): List<Album> {
        for (album in albums) {
            if (album.isBroken()) return emptyList()
        }

        val roomAlbums = albums.map { fromAlbum(it) }

        AlbumRoomDao.get().delete(*roomAlbums.toTypedArray())
        return roomAlbums
    }

    override fun save(vararg albums: Album): List<Album> {
        for (album in albums) {
            if (album.isBroken()) return emptyList()
        }

        val roomAlbums = albums.map { fromAlbum(it) }

        AlbumRoomDao.get().delete(*roomAlbums.toTypedArray())
        return roomAlbums
    }
}