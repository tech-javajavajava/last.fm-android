package technopark.andruxa.myapplication.data.additional.lastFm.artist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist

@Xml(name = "results")
class ArtistSearchXML {
    @Path("artistmatches")
    @Element
    val artists: List<ArtistInfoXML> = emptyList()

    fun toArtistList(): List<Artist> {
        artists.forEach { it.initImages() }
        return artists
    }
}