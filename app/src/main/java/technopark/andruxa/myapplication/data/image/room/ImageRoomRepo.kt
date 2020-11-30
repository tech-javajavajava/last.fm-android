package technopark.andruxa.myapplication.data.image.room

import technopark.andruxa.myapplication.data.additional.room.image.ImageRoomDao
import technopark.andruxa.myapplication.data.additional.room.image.fromImage
import technopark.andruxa.myapplication.data.image.ImageRepo
import technopark.andruxa.myapplication.models.image.Image

class ImageRoomRepo: ImageRepo {
    override fun getByName(name: String): Image {
        return ImageRoomDao.get().getByName(name)
    }

    override fun save(vararg images: Image): List<Image> {
        for (image in images) {
            if (image.isBroken()) return emptyList()
        }

        val roomImages = images.map { fromImage(it) }

        ImageRoomDao.get().save(*roomImages.toTypedArray())
        return roomImages
    }

    override fun delete(vararg images: Image): List<Image> {
        for (image in images) {
            if (image.isBroken()) return emptyList()
        }

        val roomImages = images.map { fromImage(it) }

        ImageRoomDao.get().delete(*roomImages.toTypedArray())
        return roomImages
    }
}