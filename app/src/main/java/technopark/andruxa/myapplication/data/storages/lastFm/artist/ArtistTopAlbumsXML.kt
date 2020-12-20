package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumXML

@Xml(name = "lfm")
class ArtistTopAlbumsXML {
    @Path("topalbums")
    var albums: List<AlbumXML>? = null
}