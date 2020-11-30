package technopark.andruxa.myapplication.data.additional.room.track

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase

@Dao
interface TrackRoomDao {
    @Query("SELECT * FROM TrackRoomEntity WHERE mbid = :mbid LIMIT 1")
    fun getByMbid(mbid: String): TrackRoomEntity

    @Query("SELECT * FROM TrackRoomEntity WHERE name = :name AND artistName = :artistName LIMIT 1")
    fun getByNameNArtist(name: String, artistName: String): TrackRoomEntity

    @Query("SELECT * FROM TrackRoomEntity WHERE name LIKE :name LIMIT :limit OFFSET :offset")
    fun searchByName(name: String, limit: Int, offset: Int): List<TrackRoomEntity>

    @Query("SELECT * FROM TrackRoomEntity WHERE name LIKE :name AND artistName LIKE :artistName LIMIT :limit OFFSET :offset")
    fun searchByNameNArtist(name: String, artistName: String, limit: Int, offset: Int): List<TrackRoomEntity>

    @Query("SELECT * FROM TrackRoomEntity WHERE isTop = 1 LIMIT :limit OFFSET :offset")
    fun getTop(limit: Int, offset: Int): List<TrackRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg tracks: TrackRoomEntity): Long

    @Delete
    fun delete(vararg tracks: TrackRoomEntity): Long

    companion object {
        fun get(): TrackRoomDao {
            return getRoomDatabase().trackRoomDao()
        }
    }
}