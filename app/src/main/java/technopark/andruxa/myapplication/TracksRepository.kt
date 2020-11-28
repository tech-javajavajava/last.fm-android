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

    private var progress: MutableLiveData<Progress>? = null

    fun search(query: String, limit: Int): LiveData<Progress>? {
        Log.d("tracks search", "reporitory search")
        progress?.let {
            it.value?.let {
                if (it.state == Progress.State.IN_PROGRESS) {
                    return progress
                }
            }
        }
        progress = MutableLiveData(Progress(Progress.State.IN_PROGRESS))
        search(progress!!, query, limit)
        return progress
    }

    private fun search(progress: MutableLiveData<Progress>, query: String, limit: Int) {
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
                        progress.postValue(Progress(Progress.State.SUCCESS, it.results!!.tracks!!.map{st -> TrackApi.Track(st)}))
                        return
                    }
                }
                progress.postValue(Progress(Progress.State.FAILED))
            }

            override fun onFailure(call: Call<TrackApi.TrackSearchResponse>?, t: Throwable?) {
                Log.d("track search", "failure")
                Log.d("track search", t.toString())
                progress.postValue(Progress(Progress.State.FAILED))
            }
        })
    }

    fun getChart(limit: Int): LiveData<Progress>? {
        Log.d("tracks chart", "reporitory")
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
        val api: TrackApi? = api?.trackApi
        api?.getChart(limit)?.enqueue(object : Callback<TrackApi.TrackChartResponse> {
            override fun onResponse(
                call: Call<TrackApi.TrackChartResponse>?,
                response: Response<TrackApi.TrackChartResponse>
            ) {
                Log.d("tracks chart", "response")
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.d("tracks chart", it.toString())
                        progress.postValue(Progress(Progress.State.SUCCESS, it.tracks!!.tracks!!.map{ct -> TrackApi.Track(ct)}))
                        return
                    }
                }
                progress.postValue(Progress(Progress.State.FAILED))
            }

            override fun onFailure(call: Call<TrackApi.TrackChartResponse>?, t: Throwable?) {
                Log.d("tracks chart", "failure")
                Log.d("tracks chart", t.toString())
                progress.postValue(Progress(Progress.State.FAILED))
            }
        })
    }

    class Progress(var state: State) {
        constructor(state: State, tracks: List<TrackApi.Track>?): this(state) {
            this.tracks = tracks
        }
        enum class State {
            IN_PROGRESS, SUCCESS, FAILED
        }
        var tracks: List<TrackApi.Track>? = null
    }
}