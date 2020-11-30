package technopark.andruxa.myapplication.data.additional.lastFm.user

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.user.User

@Xml
class UserInfoXML: User {
    @PropertyElement
    override var id: Int = -1
    @PropertyElement
    override var name: String = ""
    @PropertyElement
    override var realName: String = ""
    @PropertyElement
    override var url: String = ""
    @PropertyElement
    override var image: String = ""
    @PropertyElement
    override var country: String = ""
    @PropertyElement
    override var age: Int = -1
    @PropertyElement
    override var gender: String = ""
    @PropertyElement(name = "subscriber")
    override var isSubscriber: Boolean = false
    @PropertyElement
    override var playlists: Int = -1
    @PropertyElement(name = "bootstrap")
    override var isBootstrap: Boolean = false
    @PropertyElement(name = "registered")
    override var registeredTime: String = ""

    override var errorCode: Int = 0
    override var message: String = ""
}