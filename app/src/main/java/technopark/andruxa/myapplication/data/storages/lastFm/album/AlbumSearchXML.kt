package technopark.andruxa.myapplication.data.storages.lastFm.album

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class AlbumSearchXML {
    @Path("results/albummatches")
    @Element
    var albums: List<AlbumXML>? = null
}