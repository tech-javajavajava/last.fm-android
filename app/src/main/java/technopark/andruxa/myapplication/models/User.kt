package technopark.andruxa.myapplication.models

interface User {
    val id: Int
    val name: String
    val realName: String
    val url: String
    val image: String
    val country: String
    val age: Int
    val gender: String
    val isSubscriber: Boolean
    val playcount: Int
    val isBootstrap: Boolean
    val registeredTime: Int
}
