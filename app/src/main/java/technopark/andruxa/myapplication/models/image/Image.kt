package technopark.andruxa.myapplication.models.image

import android.graphics.Bitmap
import technopark.andruxa.myapplication.models.additional.CanBeBroken

interface Image: CanBeBroken {
    var name: String
    var bitmap: Bitmap?

    override fun isBroken(): Boolean {
        if (bitmap == null) return true
        return super.isBroken()
    }
}