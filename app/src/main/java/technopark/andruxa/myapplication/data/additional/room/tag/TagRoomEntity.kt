package technopark.andruxa.myapplication.data.additional.room.tag

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.tag.Tag

@Entity
class TagRoomEntity: Tag {
    @PrimaryKey override val name: String = ""
    @ColumnInfo override val url: String = ""
    @ColumnInfo override val reach: Int = -1
    @ColumnInfo override val taggings: Int = -1
    @ColumnInfo override val isStreamable: Boolean = false
    @ColumnInfo override var wikiPublished: String = ""
    @ColumnInfo override var wikiSummary: String = ""
    @ColumnInfo override var wikiContent: String = ""
    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""
}