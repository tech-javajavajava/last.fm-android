package technopark.andruxa.myapplication.data.storages.lastFm.image

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml
class ImageXML {
    @Attribute
    var size: String? = null

    @TextContent
    var url: String? = null
}