package technopark.andruxa.myapplication.data.storages.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "similartracks")
class TrackSimilarXML {
    @Element
    var tracks: List<TrackXML> = emptyList()
}