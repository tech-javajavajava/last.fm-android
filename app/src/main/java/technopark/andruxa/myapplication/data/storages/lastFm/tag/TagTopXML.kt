package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class TagTopXML {
    @Path("tags")
    @Element
    var tags: List<TagXML>? = null
}