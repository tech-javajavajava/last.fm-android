package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.artist.Artistix
import technopark.andruxa.myapplication.models.image.Image

@Xml(name = "artist")
class ArtistXML: Artistix {
    @PropertyElement
    var name: String? = null
    @PropertyElement(name = "mbid")
    var id: String? = null
    @PropertyElement
    var url: String? = null
    @PropertyElement(name = "image_small")
    var imageSmall: String? = null
    @PropertyElement
    var image: String? = null

    override fun toArtist(): Artist {
        return Artist().also {
            it.name = name
            it.id = id
            it.url = url
            if (imageSmall != null) {
                it.images.small = Image().apply { url = imageSmall as String }
            }
            if (image != null) {
                it.images.medium = Image().apply { url = image as String }
            }
        }
    }
}