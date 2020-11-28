package technopark.andruxa.myapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.network.ArtistApi

class ArtistsRepository(private var api: Api?) {
    companion object {
        fun getInstance(context: Context?): ArtistsRepository {
            return ApplicationModified.from(context).artistsRepository!!
        }
    }

    private var progress: MutableLiveData<Progress>? = null

    fun search(query: String, limit: Int): LiveData<Progress>? {
        Log.d("artists search", "reporitory search")
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
        Log.d("artists search", "repository search 2")
        val api: ArtistApi? = api?.artistApi
        api?.search(query, limit)?.enqueue(object : Callback<ArtistApi.ArtistSearchResponse> {
            override fun onResponse(
                    call: Call<ArtistApi.ArtistSearchResponse>?,
                    response: Response<ArtistApi.ArtistSearchResponse>
            ) {
                Log.d("artist search", "response")
                response.body()?.let {
                    if (response.isSuccessful()) {
                        Log.d("artist search", it.toString())
                        progress.postValue(Progress(Progress.State.SUCCESS, it.results!!.artists!!.map{sa -> ArtistApi.Artist(sa)}))
                        return
                    }
                }
                progress.postValue(Progress(Progress.State.FAILED))
            }

            override fun onFailure(call: Call<ArtistApi.ArtistSearchResponse>?, t: Throwable?) {
                progress.postValue(Progress(Progress.State.FAILED))
            }
        })
    }

    fun getChart(limit: Int): LiveData<Progress>? {
        Log.d("artists chart", "reporitory")
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
        Log.d("artists chart", "repository 2")
        val api: ArtistApi? = api?.artistApi
        api?.getChart(limit)?.enqueue(object : Callback<ArtistApi.ArtistChartResponse> {
            override fun onResponse(
                call: Call<ArtistApi.ArtistChartResponse>?,
                response: Response<ArtistApi.ArtistChartResponse>
            ) {
                Log.d("artists chart", "response")
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.d("artists chart", it.toString())
                        progress.postValue(Progress(Progress.State.SUCCESS, it.artists!!.artists!!.map { ca -> ArtistApi.Artist(ca) })
                        )
                        return
                    }
                }
                progress.postValue(Progress(Progress.State.FAILED))
            }

            override fun onFailure(call: Call<ArtistApi.ArtistChartResponse>?, t: Throwable?) {
                Log.d("artists chart", "failure")
                Log.d("artists chart", t.toString())
                progress.postValue(Progress(Progress.State.FAILED))
            }
        })
    }

    class Progress(var state: State) {
        constructor(state: State, artists: List<ArtistApi.Artist>?): this(state) {
            this.artists = artists
        }
        enum class State {
            IN_PROGRESS, SUCCESS, FAILED
        }
        var artists: List<ArtistApi.Artist>? = null
    }
}