package technopark.andruxa.myapplication.data.storages.lastFm.tag

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.tag.Tagix

@Xml(name = "tag")
class TagXML: Tagix {
    @PropertyElement
    var name: String? = null
    @PropertyElement
    var url: String? = null
    @PropertyElement
    var count: Int? = null

    override fun toTag(): Tag {
        return Tag().also {
            it.name = name
            it.url = url
            it.count = count
        }
    }
}