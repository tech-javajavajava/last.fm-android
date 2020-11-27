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

    private var searchProgress: MutableLiveData<SearchProgress>? = null

    fun search(query: String, limit: Int): LiveData<SearchProgress>? {
        Log.d("artists search", "reporitory search")
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
                        progress.postValue(SearchProgress(SearchProgress.State.SUCCESS, it.results!!.artists))
                        return
                    }
                }
                progress.postValue(SearchProgress(SearchProgress.State.FAILED))
            }

            override fun onFailure(call: Call<ArtistApi.ArtistSearchResponse>?, t: Throwable?) {
                progress.postValue(SearchProgress(SearchProgress.State.FAILED))
            }
        })
    }

    class SearchProgress(var state: State) {
        constructor(state: State, artists: List<ArtistApi.Artist>?): this(state) {
            this.artists = artists
        }
        enum class State {
            IN_PROGRESS, SUCCESS, FAILED
        }
        var artists: List<ArtistApi.Artist>? = null
    }
}