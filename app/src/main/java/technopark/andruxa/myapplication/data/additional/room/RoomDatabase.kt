package technopark.andruxa.myapplication.data.additional

import androidx.room.Database
import technopark.andruxa.myapplication.data.user.room.UserRoomDao
import androidx.room.RoomDatabase
import technopark.andruxa.myapplication.data.user.room.UserRoomEntity

@Database(entities = [UserRoomEntity::class], version = 1)
abstract class RoomDataBase: RoomDatabase() {
    abstract fun userRoomDao(): UserRoomDao
}