package technopark.andruxa.myapplication.data.additional.lastFm.session

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class SessionResponseXML {
    @Attribute
    var status: String = ""
    @Path("session")
    @PropertyElement(name = "session_key")
    var sessionKey: String = ""

    @Path("session")
    @PropertyElement(name = "api_sig")
    var apiSig: String = ""
}