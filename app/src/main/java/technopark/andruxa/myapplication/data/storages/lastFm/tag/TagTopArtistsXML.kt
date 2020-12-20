package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.artist.ArtistXML

@Xml(name = "lfm")
class TagTopArtistsXML {
    @Path("topartists")
    @Element
    var artists: List<ArtistXML>? = null
}
