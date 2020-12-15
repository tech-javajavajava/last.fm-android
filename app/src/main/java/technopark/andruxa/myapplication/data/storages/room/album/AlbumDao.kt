package technopark.andruxa.myapplication.data.storages.room.album

import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM AlbumEntity WHERE id = :id LIMIT 1")
    fun getById(id: String): AlbumEntity?
    
    @Query("SELECT * FROM AlbumEntity WHERE name = :name AND artistName = :artistName LIMIT 1")
    fun getByNameNArtist(name: String, artistName: String): AlbumEntity?

    @Query("SELECT * FROM AlbumEntity WHERE name LIKE :name LIMIT :limit OFFSET :offset")
    fun search(name: String, limit: Int, offset: Int): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg albums: AlbumEntity)

    @Delete
    fun delete(vararg albums: AlbumEntity)
}