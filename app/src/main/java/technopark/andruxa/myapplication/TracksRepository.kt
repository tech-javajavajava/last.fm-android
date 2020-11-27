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
    var tracks: List<TrackApi.Track>? = null

    fun search(query: String, limit: Int): LiveData<SearchProgress>? {
        Log.d("tracks search", "reporitory search")
        if (searchProgress != null && searchProgress!!.value == SearchProgress.IN_PROGRESS) {
            return searchProgress
        }
        searchProgress = MutableLiveData(SearchProgress.IN_PROGRESS)
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
                if (response.isSuccessful && response.body() != null) {
                    Log.d("track search", response.body().toString())
                    Log.d("track search", response.body()!!.results.toString())
                    Log.d("track search", response.body()!!.results!!.tracks.toString())
                    tracks = response.body()!!.results!!.tracks
                    progress.postValue(SearchProgress.SUCCESS)
                    return
                }
                progress.postValue(SearchProgress.FAILED)
            }

            override fun onFailure(call: Call<TrackApi.TrackSearchResponse>?, t: Throwable?) {
                Log.d("track search", "failure")
                Log.d("track search", t.toString())
                progress.postValue(SearchProgress.FAILED)
            }
        })
    }

    enum class SearchProgress {
        IN_PROGRESS, SUCCESS, FAILED
    }
}