package technopark.andruxa.myapplication.data.session

interface SessionRepo {
    fun login(login: String, password: String): Boolean
    fun logout(): Boolean
}