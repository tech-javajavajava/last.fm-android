package technopark.andruxa.myapplication.data.additional.room

import androidx.room.Database
import technopark.andruxa.myapplication.data.additional.room.user.UserRoomDao
import androidx.room.RoomDatabase
import technopark.andruxa.myapplication.data.additional.room.user.UserRoomEntity

@Database(entities = [UserRoomEntity::class], version = 1)
abstract class RoomDataBase: RoomDatabase() {
    abstract fun userRoomDao(): UserRoomDao
}