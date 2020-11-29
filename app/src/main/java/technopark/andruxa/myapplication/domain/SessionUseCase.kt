package technopark.andruxa.myapplication.domain

interface SessionUseCase {
    fun Login(username: String,password:String): Boolean
    fun Logout(): Boolean
}