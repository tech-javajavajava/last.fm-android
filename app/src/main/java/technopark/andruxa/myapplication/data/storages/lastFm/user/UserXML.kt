package technopark.andruxa.myapplication.data.storages.lastFm.user

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.user.User
import technopark.andruxa.myapplication.models.user.UserIx

@Xml(name = "user")
class UserXML : UserIx {
    @PropertyElement
    var id: String? = null
    @PropertyElement
    var name: String? = null
    @PropertyElement
    var realName: String? = null
    @PropertyElement
    var url: String? = null
    @PropertyElement
    var image: String? = null
    @PropertyElement
    var country: String? = null
    @PropertyElement
    var age: Int? = null
    @PropertyElement
    var gender: String? = null
    @PropertyElement(name = "subscriber")
    var isSubscriber: Boolean? = null
    @PropertyElement
    var playlists: Int? = null
    @PropertyElement(name = "bootstrap")
    var isBootstrap: Boolean? = null
    @PropertyElement
    var registeredTime: String? = null

    override fun toUser(): User {
        return User().also {
            it.id = id
            it.name = name
            it.realName = realName
            it.url = url
            it.image = image
            it.country = country
            it.age = age
            it.gender = gender
            it.isSubscriber = isSubscriber
            it.playlists = playlists
            it.isBootstrap = isBootstrap
            it.registeredTime = registeredTime
        }
    }
}