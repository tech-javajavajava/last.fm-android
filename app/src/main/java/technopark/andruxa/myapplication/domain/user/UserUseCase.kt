package technopark.andruxa.myapplication.domain.user

import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.domain.UserUseCase
import technopark.andruxa.myapplication.models.user.User

class UserUseCase(val userRepo: UserRepo) : UserUseCase {
    override fun GetUserInfo(ID: Int): User {
        val user = userRepo.getById(ID)
        return user
    }
}