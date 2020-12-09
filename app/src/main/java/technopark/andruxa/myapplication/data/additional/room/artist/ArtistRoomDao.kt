package technopark.andruxa.myapplication.data.additional.room.artist

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase

@Dao
interface ArtistRoomDao {
    @Query("SELECT * FROM ArtistRoomEntity WHERE mbid = :mbid LIMIT 1")
    fun get(mbid: String): ArtistRoomEntity

    @Query("SELECT * FROM ArtistRoomEntity WHERE isTop = 1 LIMIT :limit OFFSET :offset")
    fun getTop(limit: Int, offset: Int): List<ArtistRoomEntity>

    @Query("SELECT * FROM ArtistRoomEntity WHERE name LIKE :name LIMIT :limit OFFSET :offset")
    fun search(name: String, limit: Int, offset: Int): List<ArtistRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg artists: ArtistRoomEntity)

    @Delete
    fun delete(vararg artists: ArtistRoomEntity): Int

    companion object {
        fun get(): ArtistRoomDao {
            return getRoomDatabase().artistRoomDao()
        }
    }
}