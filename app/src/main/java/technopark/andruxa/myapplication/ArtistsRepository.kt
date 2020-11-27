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
    var artists: List<ArtistApi.Artist>? = null

    fun search(query: String, limit: Int): LiveData<SearchProgress>? {
        Log.d("artists search", "reporitory search")
        if (searchProgress != null && searchProgress!!.value == SearchProgress.IN_PROGRESS) {
            return searchProgress
        }
        searchProgress = MutableLiveData(SearchProgress.IN_PROGRESS)
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
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("artist search", response.body()!!.toString())
                    artists = response.body()!!.results!!.artists
                    progress.postValue(SearchProgress.SUCCESS)
                    return
                }
                progress.postValue(SearchProgress.FAILED)
            }

            override fun onFailure(call: Call<ArtistApi.ArtistSearchResponse>?, t: Throwable?) {
                progress.postValue(SearchProgress.FAILED)
            }
        })
    }

    enum class SearchProgress {
        IN_PROGRESS, SUCCESS, FAILED
    }
}