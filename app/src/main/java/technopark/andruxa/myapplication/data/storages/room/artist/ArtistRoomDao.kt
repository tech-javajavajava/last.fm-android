package technopark.andruxa.myapplication.data.storages.room.artist

import androidx.room.*

@Dao
interface ArtistRoomDao {
    @Query("SELECT * FROM ArtistEntity WHERE mbid = :mbid LIMIT 1")
    fun getByMbid(mbid: String): ArtistEntity?

    @Query("SELECT * FROM ArtistEntity WHERE name = :name LIMIT 1")
    fun getByName(name: String): ArtistEntity?

    // TODO: no top selected, but all
    @Query("SELECT * FROM ArtistEntity LIMIT :limit OFFSET :offset")
    fun getTop(limit: Int, offset: Int): List<ArtistEntity>

    @Query("SELECT * FROM ArtistEntity WHERE name LIKE :name LIMIT :limit OFFSET :offset")
    fun searchByName(name: String, limit: Int, offset: Int): List<ArtistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg artists: ArtistEntity)

    @Delete
    fun delete(vararg artists: ArtistEntity)
}