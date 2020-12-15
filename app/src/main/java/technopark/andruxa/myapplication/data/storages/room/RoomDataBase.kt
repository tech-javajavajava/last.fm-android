package technopark.andruxa.myapplication.data.storages.room

import androidx.room.Database
import androidx.room.RoomDatabase
import technopark.andruxa.myapplication.data.storages.room.album.AlbumDao
import technopark.andruxa.myapplication.data.storages.room.album.AlbumEntity
import technopark.andruxa.myapplication.data.storages.room.artist.ArtistEntity
import technopark.andruxa.myapplication.data.storages.room.artist.ArtistRoomDao

@Database(
    entities = [
        ArtistEntity::class,
        AlbumEntity::class,
    ], version = 1, exportSchema = false
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun artistRoomDao(): ArtistRoomDao
    abstract fun albumRoomDao(): AlbumDao
}