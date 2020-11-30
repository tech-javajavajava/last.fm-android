package technopark.andruxa.myapplication.data.image.net

import android.graphics.Bitmap
import technopark.andruxa.myapplication.models.image.Image

class NetImage: Image {
    override var name: String = ""
    override var bitmap: Bitmap? = null
    override var errorCode: Int = 0
    override var message: String = ""

    override fun isBroken(): Boolean {
        if (bitmap == null) return true
        return super.isBroken()
    }
}