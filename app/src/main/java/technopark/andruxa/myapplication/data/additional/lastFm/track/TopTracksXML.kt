package technopark.andruxa.myapplication.data.additional.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "tracks")
class TopTracksXML {
    @Element
    var tracks: List<TrackInfoXML> = emptyList()
}