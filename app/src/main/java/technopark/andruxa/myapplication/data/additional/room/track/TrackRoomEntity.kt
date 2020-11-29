package technopark.andruxa.myapplication.data.additional.room.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

@Entity
class TrackRoomEntity: Track {
    @PrimaryKey override var mbid: String = ""
    @ColumnInfo override var name: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var duration: Int = -1
    @ColumnInfo override var isStreamable: Boolean = false
    @ColumnInfo override var listenerNum: Int = -1
    @ColumnInfo override var playCount: Int = -1
    @ColumnInfo var artistName: String = ""
    @ColumnInfo var albumId: Int = -1
    override var artist: Artist? = null
    override var album: Album? = null
    override var topTags: List<Tag>? = null
    @ColumnInfo override var wikiPublished: String = ""
    @ColumnInfo override var wikiSummary: String = ""
    @ColumnInfo override var wikiContent: String = ""
    override var errorCode: Int = -1
    override var message: String = ""
}