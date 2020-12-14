package technopark.andruxa.myapplication.models.album

import technopark.andruxa.myapplication.models.image.Images

class Album {
    var name: String? = null
    var artistName: String? = null
    var id: String? = null
    var url: String? = null
    var images: Images = Images()
}
