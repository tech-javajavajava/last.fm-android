package technopark.andruxa.myapplication.data.additional.room.artist

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

class ArtistRoomEntity: Artist {
    @PrimaryKey override var name: String = ""
    @ColumnInfo override var mbid: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var imageSmallUrl: String = ""
    @ColumnInfo override var imageMediumUrl: String = ""
    @ColumnInfo override var imageLargeUrl: String = ""
    @ColumnInfo override var isStreamable: Boolean = false
    @ColumnInfo override var listeners: Int = -1
    @ColumnInfo override var plays: Int = -1
    @ColumnInfo override var similar: List<Artist>? = null
    @ColumnInfo override var tags: List<Tag>? = null
    @ColumnInfo override var wikiPublished: String = ""
    @ColumnInfo override var wikiSummary: String = ""
    @ColumnInfo override var wikiContent: String = ""
    @ColumnInfo override var errorCode: Int = 0
    @ColumnInfo override var message: String = ""
}