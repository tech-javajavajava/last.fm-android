package technopark.andruxa.myapplication.data.user.lastFm

import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.models.user.User

interface UserLastFmRepo: UserRepo {
    override fun getCurrent(): User {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun saveCurrent(current: User): User {
        TODO("Not yet implemented")
    }

    override fun save(current: User): User {
        TODO("Not yet implemented")
    }

    override fun deleteCurrent(): User {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): User {
        TODO("Not yet implemented")
    }
}