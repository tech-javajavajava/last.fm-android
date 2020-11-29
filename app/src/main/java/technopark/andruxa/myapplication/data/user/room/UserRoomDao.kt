package technopark.andruxa.myapplication.data.user.room

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.getRoomDatabase
import technopark.andruxa.myapplication.models.user.User

@Dao
interface UserRoomDao {
    @Query("SELECT * FROM UserRoomEntity WHERE id = 0")
    fun getCurrent(): User

    @Query("SELECT * FROM UserRoomEntity WHERE id = :id")
    fun getById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: User): Long

    @Delete
    fun deleteUser(user: User): User
}

fun getUserRoomDao(): UserRoomDao {
    return getRoomDatabase().userRoomDao()
}
