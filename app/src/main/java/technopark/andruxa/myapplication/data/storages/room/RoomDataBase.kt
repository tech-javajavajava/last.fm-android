package technopark.andruxa.myapplication.data.storages.room

import androidx.room.Database
import androidx.room.RoomDatabase
import technopark.andruxa.myapplication.data.storages.room.album.AlbumDao
import technopark.andruxa.myapplication.data.storages.room.album.AlbumEntity
import technopark.andruxa.myapplication.data.storages.room.artist.ArtistRoomDao
import technopark.andruxa.myapplication.data.storages.room.artist.ArtistEntity
import technopark.andruxa.myapplication.data.storages.room.image.ImageRoomDao
import technopark.andruxa.myapplication.data.storages.room.image.ImageRoomEntity
import technopark.andruxa.myapplication.data.storages.room.tag.TagRoomDao
import technopark.andruxa.myapplication.data.storages.room.tag.TagRoomEntity
import technopark.andruxa.myapplication.data.storages.room.track.TrackRoomDao
import technopark.andruxa.myapplication.data.storages.room.track.TrackRoomEntity
import technopark.andruxa.myapplication.data.storages.room.user.UserRoomDao
import technopark.andruxa.myapplication.data.storages.room.user.UserRoomEntity

@Database(
    entities = [
        UserRoomEntity::class,
        TrackRoomEntity::class,
        TagRoomEntity::class,
        ArtistEntity::class,
        AlbumEntity::class,
        ImageRoomEntity::class,
    ], version = 1, exportSchema = false
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun userRoomDao(): UserRoomDao
    abstract fun trackRoomDao(): TrackRoomDao
    abstract fun tagRoomDao(): TagRoomDao
    abstract fun artistRoomDao(): ArtistRoomDao
    abstract fun albumRoomDao(): AlbumDao
    abstract fun imageRoomDao(): ImageRoomDao
}