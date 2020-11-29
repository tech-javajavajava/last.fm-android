package technopark.andruxa.myapplication.data.user.room

import technopark.andruxa.myapplication.data.additional.room.user.UserRoomDao
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.models.user.User

class UserRoomRepo: UserRepo {
    override fun getCurrent(): User {
        return UserRoomDao.get().getCurrent()
    }

    override fun getById(id: Int): User {
        return UserRoomDao.get().getById(id)
    }

    override fun saveCurrent(current: User): User {
        UserRoomDao.get().saveUser(current)
        return current
    }

    override fun save(user: User): User {
        UserRoomDao.get().saveUser(user)
        return user
    }

    override fun deleteCurrent(): User {
        val current = getById(0)
        if (current.isBroken()) {
            return current.notFound() as User
        }
        UserRoomDao.get().deleteUser(current)
        return current
    }

    override fun deleteById(id: Int): User {
        val user = getById(id)
        if (user.isBroken()) {
            return user.notFound() as User
        }
        UserRoomDao.get().deleteUser(user)
        return user
    }
}
