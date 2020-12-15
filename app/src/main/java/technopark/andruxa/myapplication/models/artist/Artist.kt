package technopark.andruxa.myapplication.models.artist

import technopark.andruxa.myapplication.models.Toppable
import technopark.andruxa.myapplication.models.image.Images

class Artist: Toppable() {
    var name: String? = null
    var id: String? = null
    var url: String? = null
    var images: Images = Images()
}