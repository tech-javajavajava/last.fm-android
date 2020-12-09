package technopark.andruxa.myapplication.data.additional.lastFm.artist

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

@Xml(name = "artist")
class ArtistInfoXML: Artist {
    @PropertyElement
    override var name: String = ""
    @PropertyElement
    override var mbid: String  = ""
    @PropertyElement
    override var url: String = ""

    override var imageSmallUrl: String = ""
    override var imageMediumUrl: String = ""
    override var imageLargeUrl: String = ""

    @PropertyElement(name = "streamable")
    override var isStreamable: Boolean = false

    @Path("stats")
    @PropertyElement
    override var listeners: Int = -1
    @Path("stats")
    @PropertyElement
    override var plays: Int = -1

    // TODO
    override var similar: List<Artist>? = null
    // TODO
    override var tags: List<Tag>? = null

    @Path("bio")
    @PropertyElement(name = "published")
    override var wikiPublished: String = ""
    @Path("bio")
    @PropertyElement(name = "summary")
    override var wikiSummary: String = ""
    @Path("bio")
    @PropertyElement(name = "content")
    override var wikiContent: String = ""

    override var errorCode: Int = 0
    override var message: String = ""

//    @PropertyElement
//    var image: List<String> = emptyList()
//    fun initImages(): Artist {
//        if (image.isNotEmpty()) imageSmallUrl = image[0]
//        if (image.size > 1) imageMediumUrl = image[1]
//        if (image.size > 2) imageLargeUrl = image[2]
//
//        return this
//    }
}