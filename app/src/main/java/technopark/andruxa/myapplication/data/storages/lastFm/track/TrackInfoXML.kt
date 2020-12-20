package technopark.andruxa.myapplication.data.storages.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class TrackInfoXML {
    @Element
    var track: TrackXML? = null
}
