package technopark.andruxa.myapplication.data.user

import technopark.andruxa.myapplication.models.User

interface UserRepo {
    fun getCurrent(): User
    fun getById(id: Int): User
}
