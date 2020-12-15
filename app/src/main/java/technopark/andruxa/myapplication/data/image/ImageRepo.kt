package technopark.andruxa.myapplication.data.image

import android.graphics.BitmapFactory
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.models.image.Image
import java.io.IOException

class ImageRepo: IImageRepo {
    override fun init(image: Image): SDataI<Image> {
        val result = SData<Image>().apply {
            setData(Image().apply { url = image.url })
        }
        result.postState(SDataI.State.Load)

        val request: Request = Request.Builder().url(image.url).build()
        LastFmStore.instance.client.newCall(request).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    result.networkError(e.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    val bytes = response.body()?.bytes()
                    bytes?.let {
                        result.data?.bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    }
                    result.postState(SDataI.State.NetOk)
                }
            }
        )

        return result
    }
}