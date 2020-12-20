package technopark.andruxa.myapplication.data.storages.lastFm.track

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.image.ImageXML
import technopark.andruxa.myapplication.data.storages.lastFm.tag.TagXML
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
    @PropertyElement
    var duration: Int? = null
    @PropertyElement
    var playcount: Int? = null

    @Path("album")
    @PropertyElement(name = "artist")
    var artistName: String? = null

    @Path("album")
    @PropertyElement(name = "name")
    var albumName: String? = null

    @Path("album")
    @Element
    var images: List<ImageXML>? = null

    @Path("toptags")
    @Element
    var topTags: List<TagXML>? = null

    @Path("wiki")
    @PropertyElement
    var published: String? = null
    @Path("wiki")
    @PropertyElement
    var summary: String? = null
    @Path("wiki")
    @PropertyElement
    var content: String? = null

    override fun toTrack(): Track {
        return Track().also {
            it.name = name
            it.url = url
            it.listeners = listeners
            it.duration = duration
            it.playcount = playcount
            it.artistName = artistName
            it.albumName = albumName
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

            it.topTags = topTags?.map { t -> t.toTag() }
            it.published = published
            it.summary = summary
            it.content = content
        }
    }
}