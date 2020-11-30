package technopark.andruxa.myapplication.data.additional.lastFm.tag

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.tag.Tag

@Xml(name = "tag")
class TagInfoXML: Tag {
    @PropertyElement
    override var name: String = ""
    @PropertyElement
    override var url: String = ""
    @PropertyElement
    override var reach: Int = -1
    @PropertyElement
    override var taggings: Int = -1
    @PropertyElement(name = "streamable")
    override var isStreamable: Boolean = false
    @Path("wiki")
    @PropertyElement(name = "published")
    override var wikiPublished: String = ""
    @Path("wiki")
    @PropertyElement(name = "summary")
    override var wikiSummary: String = ""
    @Path("wiki")
    @PropertyElement(name = "content")
    override var wikiContent: String = ""

    override var errorCode: Int = 0
    override var message: String = ""
}