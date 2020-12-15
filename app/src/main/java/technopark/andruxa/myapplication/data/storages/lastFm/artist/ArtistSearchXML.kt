package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "results")
class ArtistSearchXML {
    @Path("artistmatches")
    @Element
    var artists: List<ArtistXML> = emptyList()
}