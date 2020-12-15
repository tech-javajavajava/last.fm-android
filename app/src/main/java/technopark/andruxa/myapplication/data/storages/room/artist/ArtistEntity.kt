package technopark.andruxa.myapplication.data.storages.room.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.artist.Artistix
import technopark.andruxa.myapplication.models.image.Image

@Entity
class ArtistEntity: Artistix {
    @ColumnInfo
    var name: String? = null
    @PrimaryKey
    var mbid: String = ""
    @ColumnInfo
    var url: String? = null
    @ColumnInfo
    var imageSmall: String? = null
    @ColumnInfo
    var imageMedium: String? = null
    @ColumnInfo
    var imageLarge: String? = null

    override fun toArtist(): Artist {
        return Artist().also {
            it.name = name
            it.id = mbid
            it.url = url
            if (imageSmall != null) {
                it.images.small = Image().apply { url = imageSmall as String }
            }
            if (imageMedium != null) {
                it.images.medium = Image().apply { url = imageMedium as String }
            }
            if (imageLarge != null) {
                it.images.large = Image().apply { url = imageLarge as String }
            }
        }
    }
}
