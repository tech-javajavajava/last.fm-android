package technopark.andruxa.myapplication.data.storages.room.image

import androidx.room.*
//import technopark.andruxa.myapplication.data.storages.room.getRoomDatabase
//
//@Dao
//interface ImageRoomDao {
//    @Query("SELECT * FROM ImageRoomEntity WHERE name = :name LIMIT 1")
//    fun getByName(name: String): ImageRoomEntity
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun save(vararg images: ImageRoomEntity)
//
//    @Delete
//    fun delete(vararg images: ImageRoomEntity)
//
//    companion object {
//        fun get(): ImageRoomDao {
//            return getRoomDatabase().imageRoomDao()
//        }
//    }
//}