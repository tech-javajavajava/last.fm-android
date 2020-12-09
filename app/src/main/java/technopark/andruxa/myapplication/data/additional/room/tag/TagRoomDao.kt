package technopark.andruxa.myapplication.data.additional.room.tag

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase

@Dao
interface TagRoomDao {
    @Query("SELECT * FROM TagRoomEntity WHERE name = :name LIMIT 1")
    fun getByName(name: String): TagRoomEntity

    @Query("SELECT * FROM TagRoomEntity WHERE isTop = 1 LIMIT :limit OFFSET :offset")
    fun getTop(limit: Int, offset: Int): List<TagRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg tags: TagRoomEntity)

    @Delete
    fun delete(vararg tags: TagRoomEntity): Int

    companion object {
        fun get(): TagRoomDao {
            return getRoomDatabase().tagRoomDao()
        }
    }
}