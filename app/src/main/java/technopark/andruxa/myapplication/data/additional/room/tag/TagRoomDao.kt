package technopark.andruxa.myapplication.data.additional.room.tag

import androidx.room.*
import technopark.andruxa.myapplication.models.tag.Tag

@Dao
interface TagRoomDao {
    @Query("SELECT * FROM TagRoomEntity WHERE name = :name")
    fun getByName(name: String): Tag

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(tag: Tag): Long

    @Delete
    fun delete(tag: Tag): Long
}