package technopark.andruxa.myapplication.data.storages.room.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.album.Albumix
import technopark.andruxa.myapplication.models.image.Image

@Entity
class AlbumEntity: Albumix {
    @ColumnInfo
    var name: String? = null

    @ColumnInfo
    var artistName: String? = null

    @PrimaryKey
    var id: String = ""

    @ColumnInfo
    var url: String? = null

    @ColumnInfo
    var smallImage: String? = null

    @ColumnInfo
    var mediumImage: String? = null

    @ColumnInfo
    var largeImage: String? = null

    override fun toAlbum(): Album {
        return Album().also {
            it.id = id
            it.name = name
            it.artistName = artistName
            it.url = url
            if (smallImage != null) {
                it.images.small = Image().also { url = smallImage }
            }
            if (mediumImage != null) {
                it.images.medium = Image().also { url = mediumImage }
            }
            if (largeImage != null) {
                it.images.large = Image().also { url = largeImage }
            }
        }
    }
}
