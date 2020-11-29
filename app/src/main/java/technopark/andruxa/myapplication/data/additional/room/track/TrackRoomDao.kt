package technopark.andruxa.myapplication.data.additional.room.track

import androidx.room.*
import technopark.andruxa.myapplication.data.additional.room.getRoomDatabase
import technopark.andruxa.myapplication.models.track.Track

@Dao
interface TrackRoomDao {
    @Query("SELECT * FROM TrackRoomEntity WHERE mbid = :mbid")
    fun getByMbid(mbid: String): Track

    @Query("SELECT * FROM TrackRoomEntity WHERE name = :name AND artistName = :artistName")
    fun getByNameNArtist(name: String, artistName: String): Track

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(track: Track): Long

    @Delete
    fun delete(track: Track): Long

    companion object {
        fun get(): TrackRoomDao {
            return getRoomDatabase().trackRoomDao()
        }
    }
}