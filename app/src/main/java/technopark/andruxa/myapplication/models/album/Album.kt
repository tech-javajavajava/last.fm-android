package technopark.andruxa.myapplication.models.album

import technopark.andruxa.myapplication.models.image.Images
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

class Album {
    var name: String? = null
    var artistName: String? = null
    var id: String? = null
    var url: String? = null
    var listeners: Int? = null
    var playcount: Int? = null
    var summary: String? = null
    var content: String? = null
    var images: Images = Images()
    var tags: List<Tag>? = null
    var tracks: List<Track>? = null
}
