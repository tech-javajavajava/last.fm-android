package technopark.andruxa.myapplication.data.image

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.image.Image

interface IImageRepo {
    fun init(image: Image): SDataI<Image>
}