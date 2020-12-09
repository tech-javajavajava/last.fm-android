package technopark.andruxa.myapplication.data.additional.room.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

@Entity
class AlbumRoomEntity: Album {
    @ColumnInfo override var name: String = ""
    @ColumnInfo override var artistName: String = ""
    @PrimaryKey override var mbid: String = ""
    @ColumnInfo override var url: String = "" 
    @ColumnInfo override var release: String = ""
    @ColumnInfo override var imageSmallUrl: String = ""
    @ColumnInfo override var imageMediumUrl: String = ""
    @ColumnInfo override var imageLargeUrl: String = ""
    @ColumnInfo override var listenerNum: Int = 0
    @ColumnInfo override var playCount: Int = 0
    @Ignore override var topTags: List<Tag>? = null
    @Ignore override var tracks: List<Track>? = null
    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""
}

fun fromAlbum(album: Album): AlbumRoomEntity {
    return AlbumRoomEntity().apply {
        name = album.name
        artistName = album.artistName
        mbid = album.mbid
        url = album.url
        release = album.release
        imageSmallUrl = album.imageSmallUrl
        imageMediumUrl = album.imageMediumUrl
        imageLargeUrl = album.imageLargeUrl
        listenerNum = album.listenerNum
        playCount = album.playCount
        errorCode = album.errorCode
        message = album.message
    }
}