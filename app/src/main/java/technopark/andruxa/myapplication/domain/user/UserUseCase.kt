package technopark.andruxa.myapplication.domain.user

import android.content.Context
import technopark.andruxa.myapplication.ApplicationModified
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.domain.UserUseCase
import technopark.andruxa.myapplication.models.user.User
import technopark.andruxa.myapplication.user.UserRepository

class UserUseCase(val userRepo: UserRepo) : UserUseCase {
    companion object {
        fun getInstance(context: Context?): UserUseCase {
            return ApplicationModified.from(context).userUseCase!!
        }
    }
    override fun GetUserInfo(ID: Int): User {
        val user = userRepo.getById(ID)
        return user
    }
}