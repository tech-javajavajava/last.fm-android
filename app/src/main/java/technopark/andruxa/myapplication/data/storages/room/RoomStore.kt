package technopark.andruxa.myapplication.data.storages.room

import androidx.room.Room.databaseBuilder
import technopark.andruxa.myapplication.ApplicationModified

class RoomStore {
    companion object {
        val instance = databaseBuilder(
            ApplicationModified.context!!,
            RoomDataBase::class.java, "room-ty"
        ).build()
    }
}