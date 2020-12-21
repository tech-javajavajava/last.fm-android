package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.image.ImageXML
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
    @Element
    var images: List<ImageXML>? = null

    override fun toArtist(): Artist {
        return Artist().also {
            it.name = name
            it.id = id
            it.url = url
            images?.let { list ->
                for (image in list) {
                    if (image.size == null || image.url == null) {
                        continue
                    }
                    when (image.size) {
                        "large" -> {
                            it.images.large = Image().apply { url = image.url!! }
                        }
                        "medium" -> {
                            it.images.medium = Image().apply { url = image.url!! }
                        }
                        else -> {
                            it.images.small = Image().apply { url = image.url!! }
                        }
                    }
                }
            }
        }
    }
}