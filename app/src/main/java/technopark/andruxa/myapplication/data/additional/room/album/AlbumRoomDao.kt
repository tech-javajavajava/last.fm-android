package technopark.andruxa.myapplication.data.additional.room.album

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase

@Dao
interface AlbumRoomDao {
    @Query("SELECT * FROM AlbumRoomEntity WHERE mbid = :mbid LIMIT 1")
    fun getByMbid(mbid: String): AlbumRoomEntity
    
    @Query("SELECT * FROM AlbumRoomEntity WHERE name = :name AND artistName = :artistName LIMIT 1")
    fun getByNameNArtist(name: String, artistName: String): AlbumRoomEntity

    @Query("SELECT * FROM AlbumRoomEntity WHERE name LIKE :name LIMIT :limit OFFSET :offset")
    fun search(name: String, limit: Int, offset: Int): List<AlbumRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg albums: AlbumRoomEntity)

    @Delete
    fun delete(vararg albums: AlbumRoomEntity): Int

    companion object {
        fun get(): AlbumRoomDao {
            return getRoomDatabase().albumRoomDao()
        }
    }
}