package technopark.andruxa.myapplication.data.storages.lastFm.session

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class SessionResponseXML {
    @Attribute
    var status: String? = null
    @Path("session")
    @PropertyElement(name = "key")
    var sessionKey: String? = null

    @Path("session")
    @PropertyElement(name = "api_sig")
    var apiSig: String? = null
}