package technopark.andruxa.myapplication.data.additional.room.user

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase
import technopark.andruxa.myapplication.models.user.User

@Dao
interface UserRoomDao {
    @Query("SELECT * FROM UserRoomEntity WHERE id = 0 LIMIT 1")
    fun getCurrent(): UserRoomEntity

    @Query("SELECT * FROM UserRoomEntity WHERE id = :id LIMIT 1")
    fun getById(id: Int): UserRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserRoomEntity): Long

    @Delete
    fun deleteUser(user: UserRoomEntity): User

    companion object {
        fun get(): UserRoomDao {
            return getRoomDatabase().userRoomDao()
        }
    }
}
