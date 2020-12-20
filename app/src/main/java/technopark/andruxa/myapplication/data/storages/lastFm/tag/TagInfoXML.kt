package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class TagInfoXML {
    @Element
    var tag: TagXML? = null
}