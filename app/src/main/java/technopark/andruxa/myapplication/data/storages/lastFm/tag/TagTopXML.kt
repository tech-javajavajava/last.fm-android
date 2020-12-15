package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "tags")
class TagTopXML {
    @Element
    var tags: List<TagXML> = emptyList()
}