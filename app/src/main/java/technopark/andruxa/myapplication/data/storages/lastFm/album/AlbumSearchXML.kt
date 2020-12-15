package technopark.andruxa.myapplication.data.storages.lastFm.album

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "results")
class AlbumSearchXML {
    @Path("albummatches")
    @Element
    var albums: List<AlbumXML> = List(0) { AlbumXML() }
}