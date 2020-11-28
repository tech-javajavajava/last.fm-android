package technopark.andruxa.myapplication

import android.content.Context
import android.graphics.Bitmap
import technopark.andruxa.myapplication.network.Api

class ImagesRepository(private var api: Api?) {
    companion object {
        fun getInstance(context: Context?): ImagesRepository {
            return ApplicationModified.from(context).imagesRepository!!
        }
    }

    fun getByUrl(url: String): Bitmap {
        return api!!.getImageByUrl(url)
    }
}