package technopark.andruxa.myapplication.models.user

import technopark.andruxa.myapplication.models.CanBeBroken
import java.util.*

interface User: CanBeBroken {
    var id: Int
    var name: String
    var realName: String
    var url: String
    var image: String
    var country: String
    var age: Int
    var gender: String
    var isSubscriber: Boolean
    var playlists: Int
    var isBootstrap: Boolean
    var registeredTime: Calendar
}
