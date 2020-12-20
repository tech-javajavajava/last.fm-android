package technopark.andruxa.myapplication.data.storages.lastFm.user

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "lfm")
class UserInfoXML {
    @Element
    var user: UserXML? = null
}