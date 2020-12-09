package technopark.andruxa.myapplication.data.additional.lastFm.album

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.album.Album

@Xml(name = "results")
class AlbumSearchXML {
    @Path("albummatches")
    @Element
    val albums: List<AlbumInfoXML> = emptyList()

    fun toAlbumList(): List<Album> {
        albums.forEach { it.initImages() }
        return albums
    }
}