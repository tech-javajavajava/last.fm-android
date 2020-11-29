package technopark.andruxa.myapplication.domain

import technopark.andruxa.myapplication.models.user.User

interface UserUseCase {
    fun GetUserInfo(ID:Int): User
}