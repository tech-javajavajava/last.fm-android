package technopark.andruxa.myapplication.data.additional.room

import androidx.room.Database
import technopark.andruxa.myapplication.data.additional.room.user.UserRoomDao
import androidx.room.RoomDatabase
import technopark.andruxa.myapplication.data.additional.room.album.AlbumRoomDao
import technopark.andruxa.myapplication.data.additional.room.album.AlbumRoomEntity
import technopark.andruxa.myapplication.data.additional.room.artist.ArtistRoomDao
import technopark.andruxa.myapplication.data.additional.room.artist.ArtistRoomEntity
import technopark.andruxa.myapplication.data.additional.room.tag.TagRoomDao
import technopark.andruxa.myapplication.data.additional.room.tag.TagRoomEntity
import technopark.andruxa.myapplication.data.additional.room.track.TrackRoomDao
import technopark.andruxa.myapplication.data.additional.room.track.TrackRoomEntity
import technopark.andruxa.myapplication.data.additional.room.user.UserRoomEntity

@Database(
    entities = [
        UserRoomEntity::class,
        TrackRoomEntity::class,
        TagRoomEntity::class,
        ArtistRoomEntity::class,
        AlbumRoomEntity::class,
    ], version = 1
)
abstract class RoomDataBase : RoomDatabase() {
    abstract fun userRoomDao(): UserRoomDao
    abstract fun trackRoomDao(): TrackRoomDao
    abstract fun tagRoomDao(): TagRoomDao
    abstract fun artistRoomDao(): ArtistRoomDao
    abstract fun albumRoomDao(): AlbumRoomDao
}