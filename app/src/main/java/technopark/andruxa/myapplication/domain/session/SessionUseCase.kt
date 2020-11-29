package technopark.andruxa.myapplication.domain.session

import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.domain.SessionUseCase

class SessionUseCase(val sessionRepo:UserRepo):SessionUseCase {
    override fun Login(username: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun Logout(): Boolean {
        TODO("Not yet implemented")
    }
}