package technopark.andruxa.myapplication.data.additional.lastFm.album

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

@Xml(name = "album")
class AlbumInfoXML: Album {
    @PropertyElement
    override var name: String = ""
    @PropertyElement(name = "artist")
    override var artistName: String = ""
    @PropertyElement
    override var mbid: String = ""
    @PropertyElement
    override var url: String = ""
    @PropertyElement(name = "releasedate")
    override var release: String = ""

    override var imageSmallUrl: String = ""
    override var imageMediumUrl: String = ""
    override var imageLargeUrl: String = ""

    // TODO
    var image: List<String> = emptyList()

    @PropertyElement(name = "listeners")
    override var listenerNum: Int = -1
    @PropertyElement(name = "playcount")
    override var playCount: Int = -1

    // TODO
    override var topTags: List<Tag>? = null
    // TODO
    override var tracks: List<Track>? = null


    override var errorCode: Int = 0
    override var message: String = ""

    override fun isBroken(): Boolean {
        if (this.name.isEmpty()) return true
        return super.isBroken()
    }

    fun initImages(): Album {
        if (image.isNotEmpty()) imageSmallUrl = image[0]
        if (image.size > 1) imageMediumUrl = image[1]
        if (image.size > 2) imageLargeUrl = image[2]

        return this
    }
}