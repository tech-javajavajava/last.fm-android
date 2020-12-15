package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "artists")
class ArtistsTopXML {
    @Element
    var artists: List<ArtistXML> = emptyList()
}
