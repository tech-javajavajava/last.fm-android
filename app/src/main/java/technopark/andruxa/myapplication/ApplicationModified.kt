package technopark.andruxa.myapplication

import android.app.Application
import android.content.Context

class ApplicationModified : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: Context? = null
        fun from(context: Context?): ApplicationModified {
            return context?.applicationContext as ApplicationModified
        }
    }
}