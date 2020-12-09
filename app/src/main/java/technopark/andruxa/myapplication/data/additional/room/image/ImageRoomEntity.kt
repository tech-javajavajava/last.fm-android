package technopark.andruxa.myapplication.data.additional.room.image

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import technopark.andruxa.myapplication.models.image.Image
import java.io.ByteArrayOutputStream

@Entity
class ImageRoomEntity: Image {
    @PrimaryKey override var name: String = ""
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var blob: ByteArray = byteArrayOf()
    @Ignore override var errorCode: Int = 0
    @Ignore override var message: String = ""
    @Ignore override var bitmap: Bitmap? = null
}

fun fromImage(image: Image): ImageRoomEntity {
    return  ImageRoomEntity().apply {
        name = image.name
        bitmap = image.bitmap
        if (bitmap != null) {
            val stream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.PNG, 90, stream)
            blob = stream.toByteArray()
        }
    }
}

