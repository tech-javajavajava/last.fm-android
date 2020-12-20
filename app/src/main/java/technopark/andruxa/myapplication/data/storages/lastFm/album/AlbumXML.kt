package technopark.andruxa.myapplication.data.storages.lastFm.album

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.image.ImageXML
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.album.Albumix
import technopark.andruxa.myapplication.models.image.Image

@Xml(name = "album")
class AlbumXML: Albumix {
    @PropertyElement
    var name: String? = null
    @PropertyElement(name = "artist")
    var artistName: String? = null
    @PropertyElement
    var id: String? = null
    @PropertyElement
    var url: String? = null
    @Element
    var images: List<ImageXML>? = null

    override fun toAlbum(): Album {
        return Album().also {
            it.name = name
            it.artistName = artistName
            it.id = id
            it.url = url
            if (images != null) {
                for (image in images!!) {
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