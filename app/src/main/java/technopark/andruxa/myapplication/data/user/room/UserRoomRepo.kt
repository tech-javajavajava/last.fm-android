package technopark.andruxa.myapplication.data.user.room

import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.models.user.User

class UserRoomRepo: UserRepo {
    override fun getCurrent(): User {
        return getUserRoomDao().getCurrent()
    }

    override fun getById(id: Int): User {
        return getUserRoomDao().getById(id)
    }

    override fun saveCurrent(current: User): User {
        getUserRoomDao().saveUser(current)
        return current
    }

    override fun save(user: User): User {
        getUserRoomDao().saveUser(user)
        return user
    }

    override fun deleteCurrent(): User {
        val current = getById(0)
        if (current.isBroken()) {
            return current.notFound() as User
        }
        getUserRoomDao().deleteUser(current)
        return current
    }

    override fun deleteById(id: Int): User {
        val user = getById(id)
        if (user.isBroken()) {
            return user.notFound() as User
        }
        getUserRoomDao().deleteUser(user)
        return user
    }
}
