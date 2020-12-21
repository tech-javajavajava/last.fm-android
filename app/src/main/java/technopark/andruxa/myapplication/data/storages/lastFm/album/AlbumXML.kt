package technopark.andruxa.myapplication.data.storages.lastFm.album

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.image.ImageXML
import technopark.andruxa.myapplication.data.storages.lastFm.tag.TagXML
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackXML
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.album.Albumix
import technopark.andruxa.myapplication.models.image.Image
import java.lang.Integer.parseInt

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
    @PropertyElement
    var listeners: String? = null
    @PropertyElement
    var playcount: String? = null
    @Element
    var tags: List<TagXML>? = null
    @Element
    var tracks: List<TrackXML>? = null
    @Path("wiki")
    @PropertyElement
    var summary: String? = null
    @Path("wiki")
    @PropertyElement
    var content: String? = null

    override fun toAlbum(): Album {
        return Album().also {
            it.name = name
            it.artistName = artistName
            it.id = id
            it.url = url
            listeners?.let { it1 -> it.listeners = parseInt(it1) }
            playcount?.let { it1 -> it.playcount = parseInt(it1) }
            it.summary = summary
            it.content = content
            it.tags = tags?.map { t -> t.toTag() }
            it.tracks = tracks?.map { t -> t.toTrack() }
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