package technopark.andruxa.myapplication.data.storages.lastFm.user

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackXML

@Xml(name = "lovedtracks")
class UserLovedXML {
    @Element
    var tracks: List<TrackXML>? = null
}