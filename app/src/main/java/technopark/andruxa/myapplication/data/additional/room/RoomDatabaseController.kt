package technopark.andruxa.myapplication.data.additional.room

import androidx.room.Room.databaseBuilder
import technopark.andruxa.myapplication.ApplicationModified

class RoomDatabaseController {
    companion object {
        val db = databaseBuilder(
            ApplicationModified.context!!,
            RoomDataBase::class.java, "database-name"
        ).build()
    }
}

fun getRoomDatabase(): RoomDataBase {
    return RoomDatabaseController.db
}