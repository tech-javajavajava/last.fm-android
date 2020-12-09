package technopark.andruxa.myapplication.data.image.net

import android.graphics.BitmapFactory
import technopark.andruxa.myapplication.data.image.ImageRepo
import technopark.andruxa.myapplication.models.image.Image
import java.net.URL

class ImageNetRepo : ImageRepo {
    override fun getByName(name: String): Image {
        return NetImage().apply { setNotPermitted() }
    }

    override fun getByURL(url: String): Image {
        try {
            URL(url)
        } catch (t: Throwable) {
            return NetImage().apply { setBadRequest() }
        }

        return NetImage().apply {
            bitmap = BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
        }
    }

    override fun save(vararg images: Image): List<Image> {
        return emptyList()
    }

    override fun delete(vararg images: Image): List<Image> {
        return emptyList()
    }
}