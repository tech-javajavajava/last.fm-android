package technopark.andruxa.myapplication.data.user

import technopark.andruxa.myapplication.models.user.User

interface UserRepo {
    fun getCurrent(): User
    fun getById(id: Int): User

    fun saveCurrent(current: User): User
    fun save(current: User): User

    fun deleteCurrent(): User
    fun deleteById(id: Int): User
}
