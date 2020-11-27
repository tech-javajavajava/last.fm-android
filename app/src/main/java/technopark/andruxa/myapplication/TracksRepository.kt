package technopark.andruxa.myapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.network.TrackApi

class TracksRepository(private var api: Api?) {
    companion object {
        fun getInstance(context: Context?): TracksRepository {
            return ApplicationModified.from(context).tracksRepository!!
        }
    }

    private var searchProgress: MutableLiveData<SearchProgress>? = null

    fun search(query: String, limit: Int): LiveData<SearchProgress>? {
        Log.d("tracks search", "reporitory search")
        searchProgress?.let {
            it.value?.let {
                if (it.state == SearchProgress.State.IN_PROGRESS) {
                    return searchProgress
                }
            }
        }
        searchProgress = MutableLiveData(SearchProgress(SearchProgress.State.IN_PROGRESS))
        search(searchProgress!!, query, limit)
        return searchProgress
    }

    private fun search(progress: MutableLiveData<SearchProgress>, query: String, limit: Int) {
        Log.d("tracks search", "repository search 2")
        val api: TrackApi? = api?.trackApi
        api?.search(query, limit)?.enqueue(object : Callback<TrackApi.TrackSearchResponse> {
            override fun onResponse(
                    call: Call<TrackApi.TrackSearchResponse>?,
                    response: Response<TrackApi.TrackSearchResponse>
            ) {
                Log.d("track search", "response")
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.d("track search", it.toString())
                        progress.postValue(SearchProgress(SearchProgress.State.SUCCESS, it.results!!.tracks))
                        return
                    }
                }
                progress.postValue(SearchProgress(SearchProgress.State.FAILED))
            }

            override fun onFailure(call: Call<TrackApi.TrackSearchResponse>?, t: Throwable?) {
                Log.d("track search", "failure")
                Log.d("track search", t.toString())
                progress.postValue(SearchProgress(SearchProgress.State.FAILED))
            }
        })
    }

    class SearchProgress(var state: State) {
        constructor(state: State, tracks: List<TrackApi.Track>?): this(state) {
            this.tracks = tracks
        }
        enum class State {
            IN_PROGRESS, SUCCESS, FAILED
        }
        var tracks: List<TrackApi.Track>? = null
    }
}