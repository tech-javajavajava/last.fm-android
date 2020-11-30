package technopark.andruxa.myapplication.data.artist.room

import technopark.andruxa.myapplication.data.additional.room.artist.ArtistRoomDao
import technopark.andruxa.myapplication.data.additional.room.artist.fromArtist
import technopark.andruxa.myapplication.data.artist.ArtistRepo
import technopark.andruxa.myapplication.models.artist.Artist


class ArtistRoomRepo: ArtistRepo {
    override fun getByMbid(
        mbid: String,
        autoCorrect: Boolean?,
        userName: String?,
        lang: String?
    ): Artist {
        return ArtistRoomDao.get().get(mbid)
    }

    override fun searchByName(
        name: String,
        limit: Int,
        page: Int
    ): List<Artist> {
        return ArtistRoomDao.get().search(name, limit, page)
    }

    override fun delete(vararg artists: Artist): List<Artist> {
        for (artist in artists) {
            if (artist.isBroken()) return emptyList()
        }

        val roomArtists = artists.map { fromArtist(it) }

        ArtistRoomDao.get().delete(*roomArtists.toTypedArray())
        return roomArtists
    }

    override fun save(vararg artists: Artist): List<Artist> {
        for (artist in artists) {
            if (artist.isBroken()) return emptyList()
        }

        val roomArtists = artists.map { fromArtist(it) }

        ArtistRoomDao.get().save(*roomArtists.toTypedArray())
        return roomArtists
    }

    override fun getTop(limit: Int, page: Int): List<Artist> {
        return ArtistRoomDao.get().getTop(limit, page)
    }

    override fun setTop(vararg artists: Artist): List<Artist> {
        for (artist in artists) {
            if (artist.isBroken()) return emptyList()
        }

        val roomArtists = artists.map { fromArtist(it).apply { isTop = true } }

        ArtistRoomDao.get().save(*roomArtists.toTypedArray())
        return roomArtists
    }
}