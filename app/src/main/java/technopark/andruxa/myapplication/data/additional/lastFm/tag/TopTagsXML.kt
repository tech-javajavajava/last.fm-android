package technopark.andruxa.myapplication.data.additional.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "tags")
class TopTagsXML {
    @Element
    val tags: List<TagInfoXML> = emptyList()
}