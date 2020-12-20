package technopark.andruxa.myapplication.models.track

import technopark.andruxa.myapplication.models.image.Images
import technopark.andruxa.myapplication.models.tag.Tag

class Track {
    var name: String? = null
    var url: String? = null
    var listeners: Int? = null
    var artistName: String? = null
    var albumArtist: String? = null
    var albumName: String? = null
    var images: Images = Images()
    var duration: Int? = null
    var playcount: Int? = null
    var topTags: List<Tag>? = null
    var published: String? = null
    var summary: String? = null
    var content: String? = null
}
