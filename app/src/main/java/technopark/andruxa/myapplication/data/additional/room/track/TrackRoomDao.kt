package technopark.andruxa.myapplication.data.additional.room.track

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase
import technopark.andruxa.myapplication.models.track.Track

@Dao
interface TrackRoomDao {
    @Query("SELECT * FROM TrackRoomEntity WHERE mbid = :mbid LIMIT 1")
    fun getByMbid(mbid: String): TrackRoomEntity

    @Query("SELECT * FROM TrackRoomEntity WHERE name = :name AND artistName = :artistName LIMIT 1")
    fun getByNameNArtist(name: String, artistName: String): TrackRoomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(track: TrackRoomEntity): Long

    @Delete
    fun delete(track: TrackRoomEntity): Long

    companion object {
        fun get(): TrackRoomDao {
            return getRoomDatabase().trackRoomDao()
        }
    }
}