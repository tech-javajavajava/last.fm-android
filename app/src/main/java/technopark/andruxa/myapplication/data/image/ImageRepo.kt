package technopark.andruxa.myapplication.data.image

import technopark.andruxa.myapplication.models.image.Image

interface ImageRepo {
    fun getByName(name: String): Image
    fun save(vararg images: Image): List<Image>
    fun delete(vararg images: Image): List<Image>
}