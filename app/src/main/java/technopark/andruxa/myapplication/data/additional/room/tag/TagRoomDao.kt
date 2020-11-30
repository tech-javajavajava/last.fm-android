package technopark.andruxa.myapplication.data.additional.room.tag

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase
import technopark.andruxa.myapplication.models.tag.Tag

@Dao
interface TagRoomDao {
    @Query("SELECT * FROM TagRoomEntity WHERE name = :name LIMIT 1")
    fun getByName(name: String): Tag

    @Query("SELECT * FROM TagRoomEntity WHERE isTop = 1")
    fun getTop(): List<Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg tags: Tag): Long

    @Delete
    fun delete(vararg tags: Tag): Long

    companion object {
        fun get(): TagRoomDao {
            return getRoomDatabase().tagRoomDao()
        }
    }
}