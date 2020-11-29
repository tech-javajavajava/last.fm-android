package technopark.andruxa.myapplication.data.track.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.additional.Wiki
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track
import java.util.*

@Entity
class TrackRoomEntity: Track {
    @PrimaryKey override var id: Int = -1
    @ColumnInfo override var name: String = ""
    @ColumnInfo override var mbid: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var duration: Int = -1
    @ColumnInfo override var isStreamable: Boolean = false
    @ColumnInfo override var listenerNum: Int = -1
    @ColumnInfo override var playCount: Int = -1
    @ColumnInfo var wikiPublished: Calendar = Calendar.getInstance()
    @ColumnInfo var wikiSummary: String = ""
    @ColumnInfo var wikiContent: String = ""

    @ColumnInfo var artistId: Int = -1
    @ColumnInfo var albumId: Int = -1

    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""

    @Ignore override lateinit var artist: Artist
    @Ignore override lateinit var album: Album
    @Ignore override lateinit var topTags: List<Tag>
    @Ignore override lateinit var wiki: Wiki
}