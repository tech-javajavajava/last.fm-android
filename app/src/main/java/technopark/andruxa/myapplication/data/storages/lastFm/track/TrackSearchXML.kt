package technopark.andruxa.myapplication.data.storages.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class TrackSearchXML {
    @Path("results/trackmatches")
    @Element
    var tracks: List<TrackXML>? = null
}