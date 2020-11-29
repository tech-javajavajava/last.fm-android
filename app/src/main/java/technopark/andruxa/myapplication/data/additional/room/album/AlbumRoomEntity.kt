package technopark.andruxa.myapplication.data.additional.room.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

@Entity
class AlbumRoomEntity: Album {
    @ColumnInfo override var name: String = ""
    @ColumnInfo override var artistName: String = ""
    @PrimaryKey override var id: Int = -1
    @ColumnInfo override var mbid: String = ""
    @ColumnInfo override var url: String = "" 
    @ColumnInfo override var release: String = ""
    @ColumnInfo override var imageSmallUrl: String = ""
    @ColumnInfo override var imageMediumUrl: String = ""
    @ColumnInfo override var imageLargeUrl: String = ""
    @ColumnInfo override var listenerNum: Int = 0
    @ColumnInfo override var playCount: Int = 0
    override var topTags: List<Tag>? = null
    override var tracks: List<Track>? = null
    @ColumnInfo override var errorCode: Int = 0
    @ColumnInfo override var message: String = ""
}