package technopark.andruxa.myapplication.data.additional.room.artist

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.image.Image
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.wiki.Wiki

class ArtistRoomEntity: Artist {
    @PrimaryKey override var name: String = ""
    @ColumnInfo override var mbid: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var image: Image =
    @ColumnInfo override var isStreamable: Boolean
    @ColumnInfo override var listeners: Int
    @ColumnInfo override var plays: Int
    @ColumnInfo override var similar: List<Artist>
    @ColumnInfo override var tags: List<Tag>
    @ColumnInfo override var bio: Wiki
    @ColumnInfo override var errorCode: Int
    @ColumnInfo override var message: String
}