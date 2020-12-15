package technopark.andruxa.myapplication.data.storages.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.image.ImageXML
import technopark.andruxa.myapplication.models.image.Image
import technopark.andruxa.myapplication.models.track.Track
import technopark.andruxa.myapplication.models.track.Trackix

@Xml(name = "track")
class TrackXML: Trackix {
    @PropertyElement
    var name: String? = null
    @PropertyElement
    var url: String? = null
    @PropertyElement
    var listeners: Int? = null
    @PropertyElement(name = "artist")
    var artistName: String? = null
    @Element
    var images: List<ImageXML> = List(0) { ImageXML() }

    override fun toTrack(): Track {
        return Track().also {
            it.name = name
            it.url = url
            it.listeners = listeners
            it.artistName = artistName
            for (image in images) {
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