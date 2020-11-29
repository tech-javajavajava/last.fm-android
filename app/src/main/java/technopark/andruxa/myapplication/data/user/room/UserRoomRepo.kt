package technopark.andruxa.myapplication.data.user.room

import androidx.room.*
import technopark.andruxa.myapplication.data.user.UserRepo
import technopark.andruxa.myapplication.models.notFound
import technopark.andruxa.myapplication.models.user.User

@Dao
interface UserRoomRepo: UserRepo {
    @Query("SELECT * FROM UserRoomModel WHERE id = 0")
    override fun getCurrent(): User

    @Query("SELECT * FROM UserRoomModel WHERE id = :id")
    override fun getById(id: Int): User

    override fun saveCurrent(current: User): User {
        val tmp = current.id
        current.id = 0
        saveUser(current)
        current.id = tmp
        return current
    }

    override fun save(current: User): User {
        saveUser(current)
        return current
    }

    override fun deleteById(id: Int): User {
        val user = getById(id)
        if (user.id < 0) {
            return notFound(user) as User
        }
        deleteUser(user)
        return user
    }

    override fun deleteCurrent(): User {
        return deleteById(0)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User): Long

    @Delete
    fun deleteUser(user: User): User
}
