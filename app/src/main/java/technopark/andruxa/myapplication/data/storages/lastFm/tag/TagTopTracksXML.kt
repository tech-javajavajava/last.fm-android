package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.artist.ArtistXML
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackXML

@Xml(name = "lfm")
class TagTopTracksXML {
    @Path("toptracks")
    @Element
    var tracks: List<TrackXML>? = null
}
