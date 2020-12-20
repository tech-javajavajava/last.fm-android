package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class ArtistInfoXML {
    @Element
    var artist: ArtistXML? = null
}