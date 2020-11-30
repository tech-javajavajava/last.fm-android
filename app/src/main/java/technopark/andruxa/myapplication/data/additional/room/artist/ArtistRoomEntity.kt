package technopark.andruxa.myapplication.data.additional.room.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

@Entity
class ArtistRoomEntity: Artist {
    @ColumnInfo override var name: String = ""
    @PrimaryKey override var mbid: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var imageSmallUrl: String = ""
    @ColumnInfo override var imageMediumUrl: String = ""
    @ColumnInfo override var imageLargeUrl: String = ""
    @ColumnInfo override var isStreamable: Boolean = false
    @ColumnInfo override var listeners: Int = -1
    @ColumnInfo override var plays: Int = -1
    @Ignore override var similar: List<Artist>? = null
    @Ignore override var tags: List<Tag>? = null
    @ColumnInfo override var wikiPublished: String = ""
    @ColumnInfo override var wikiSummary: String = ""
    @ColumnInfo override var wikiContent: String = ""
    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""
    @ColumnInfo var isTop: Boolean = false
}

fun fromArtist(artist: Artist): ArtistRoomEntity {
    return ArtistRoomEntity().apply {
        name = artist.name
        mbid = artist.mbid
        url = artist.url
        imageSmallUrl = artist.imageSmallUrl
        imageMediumUrl = artist.imageMediumUrl
        imageLargeUrl = artist.imageLargeUrl
        isStreamable = artist.isStreamable
        listeners = artist.listeners
        plays = artist.plays
        wikiPublished = artist.wikiPublished
        wikiSummary = artist.wikiSummary
        wikiContent = artist.wikiContent
        errorCode = artist.errorCode
        message = artist.message
    }
}