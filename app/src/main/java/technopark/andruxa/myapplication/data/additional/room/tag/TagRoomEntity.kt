package technopark.andruxa.myapplication.data.additional.room.tag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.tag.Tag

@Entity
class TagRoomEntity : Tag {
    @PrimaryKey override var name: String = ""
    @ColumnInfo override var url: String = ""
    @ColumnInfo override var reach: Int = -1
    @ColumnInfo override var taggings: Int = -1
    @ColumnInfo override var isStreamable: Boolean = false
    @ColumnInfo override var wikiPublished: String = ""
    @ColumnInfo override var wikiSummary: String = ""
    @ColumnInfo override var wikiContent: String = ""
    @ColumnInfo var isTop: Boolean = false
    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""
}

fun fromTag(tag: Tag): TagRoomEntity {
    return TagRoomEntity().apply {
        name = tag.name
        url = tag.url
        reach = tag.reach
        taggings = tag.taggings
        isStreamable = tag.isStreamable
        wikiPublished = tag.wikiPublished
        wikiSummary = tag.wikiSummary
        wikiContent = tag.wikiContent
        errorCode = tag.errorCode
        message = tag.message
    }
}