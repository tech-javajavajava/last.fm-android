package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackXML

@Xml(name = "lfm")
class ArtistTopTracksXML {
    @Path("toptracks")
    var tracks: List<TrackXML>? = null
}