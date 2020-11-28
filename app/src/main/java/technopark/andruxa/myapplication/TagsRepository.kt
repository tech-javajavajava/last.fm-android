package technopark.andruxa.myapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.network.TagApi

class TagsRepository(private var api: Api?) {
    companion object {
        fun getInstance(context: Context?): TagsRepository {
            return ApplicationModified.from(context).tagsRepository!!
        }
    }

    private var progress: MutableLiveData<Progress>? = null

    fun getChart(limit: Int): LiveData<Progress>? {
        Log.d("tags chart", "reporitory")
        progress?.let {
            it.value?.let {
                if (it.state == Progress.State.IN_PROGRESS) {
                    return progress
                }
            }
        }
        progress = MutableLiveData(Progress(Progress.State.IN_PROGRESS))
        getChart(progress!!, limit)
        return progress
    }

    private fun getChart(progress: MutableLiveData<Progress>, limit: Int) {
        Log.d("tracks chart", "repository 2")
        val api: TagApi? = api?.tagApi
        api?.getChart(limit)?.enqueue(object : Callback<TagApi.TagChartResponse> {
            override fun onResponse(
                call: Call<TagApi.TagChartResponse>?,
                response: Response<TagApi.TagChartResponse>
            ) {
                Log.d("tags chart", "response")
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.d("tags chart", it.toString())
                        progress.postValue(Progress(Progress.State.SUCCESS, it.tags!!.tags))
                        return
                    }
                }
                progress.postValue(Progress(Progress.State.FAILED))
            }

            override fun onFailure(call: Call<TagApi.TagChartResponse>?, t: Throwable?) {
                progress.postValue(Progress(Progress.State.FAILED))
            }
        })
    }

    class Progress(var state: State) {
        constructor(state: State, tags: List<TagApi.Tag>?): this(state) {
            this.tags = tags
        }
        enum class State {
            IN_PROGRESS, SUCCESS, FAILED
        }
        var tags: List<TagApi.Tag>? = null
    }

    fun getImageUrl(tag: String): MutableLiveData<String?> {
        val liveUrl: MutableLiveData<String?> = MutableLiveData()
        api?.tagApi?.getTopArtists(tag, 1)?.enqueue(object : Callback<TagApi.TagTopArtistsResponse> {
            override fun onResponse(
                call: Call<TagApi.TagTopArtistsResponse>?,
                response: Response<TagApi.TagTopArtistsResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        liveUrl.postValue(it.topartists!!.artists!![0].images!![0].url)
                        return
                    }
                }
                liveUrl.postValue(null)
            }

            override fun onFailure(call: Call<TagApi.TagTopArtistsResponse>?, t: Throwable?) {
                liveUrl.postValue(null)
                return
            }
        })
        return liveUrl
    }
}