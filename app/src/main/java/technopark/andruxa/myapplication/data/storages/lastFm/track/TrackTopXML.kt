package technopark.andruxa.myapplication.data.storages.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class TrackTopXML {
    @Path("tracks")
    @Element
    var tracks: List<TrackXML>? = null
}