package technopark.andruxa.myapplication.data.additional.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

@Xml(name = "track")
class TrackInfoXML: Track {
    @PropertyElement
    override var name: String = ""
    @PropertyElement
    override var mbid: String = ""
    @PropertyElement
    override var url: String = ""
    @PropertyElement
    override var duration: Int = -1
    @PropertyElement(name = "streamable")
    override var isStreamable: Boolean = false
    @PropertyElement(name = "listeners")
    override var listenerNum: Int = -1
    @PropertyElement(name = "playcount")
    override var playCount: Int = -1

    // TODO
    override var artist: Artist? = null
    // TODO
    override var album: Album? = null
    // TODO
    override var topTags: List<Tag>? = null

    @Path("wiki")
    @PropertyElement(name = "published")
    override var wikiPublished: String = ""
    @Path("wiki")
    @PropertyElement(name = "summary")
    override var wikiSummary: String = ""
    @Path("wiki")
    @PropertyElement(name = "content")
    override var wikiContent: String = ""
    override var image: String? = null
    override var errorCode: Int = 0
    override var message: String = ""
}