package technopark.andruxa.myapplication.models.track

import technopark.andruxa.myapplication.models.image.Images

class Track {
    var name: String? = null
    var url: String? = null
    var listeners: Int? = null
    var artistName: String? = null
    var images: Images = Images()
}
