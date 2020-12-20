package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumXML

@Xml(name = "lfm")
class TagTopAlbumsXML {
    @Path("topalbums")
    @Element
    var albums: List<AlbumXML>? = null
}