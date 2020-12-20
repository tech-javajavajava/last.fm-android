package technopark.andruxa.myapplication.data.storages.lastFm.album

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class AlbumInfoXML {
    @Element
    var album: AlbumXML? = null
}