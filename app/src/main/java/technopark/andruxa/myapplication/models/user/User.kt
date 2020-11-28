package technopark.andruxa.myapplication.models.user

import technopark.andruxa.myapplication.models.CanBeBroken
import java.util.*

interface User: CanBeBroken {
    val id: Int
    val name: String
    val realName: String
    val url: String
    val image: String
    val country: String
    val age: Int
    val gender: String
    val isSubscriber: Boolean
    val playlists: Int
    val isBootstrap: Boolean
    val registeredTime: Calendar
}
