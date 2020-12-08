package technopark.andruxa.myapplication.data.additional.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "results")
class TrackSearchXML {
    @Path("trackmatches")
    @Element
    var tracks: List<TrackInfoXML> = emptyList()
}