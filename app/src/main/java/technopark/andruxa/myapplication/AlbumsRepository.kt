package technopark.andruxa.myapplication

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.network.AlbumApi
import technopark.andruxa.myapplication.network.Api

class AlbumsRepository(private var api: Api?) {
    companion object {
        fun getInstance(context: Context?): AlbumsRepository {
            return ApplicationModified.from(context).albumsRepository!!
        }
    }

    private var searchProgress: MutableLiveData<SearchProgress>? = null

    fun search(query: String, limit: Int): LiveData<SearchProgress>? {
        Log.d("albums search", "reporitory search")
        if (searchProgress != null && searchProgress!!.value == SearchProgress.IN_PROGRESS) {
            return searchProgress
        }
        searchProgress = MutableLiveData(SearchProgress.IN_PROGRESS)
        search(searchProgress!!, query, limit)
        return searchProgress
    }

    private fun search(progress: MutableLiveData<SearchProgress>, query: String, limit: Int) {
        Log.d("albums search", "repository search 2")
        val api: AlbumApi? = api?.albumApi
        api?.search(query, limit)?.enqueue(object : Callback<AlbumApi.SearchResponse> {
            override fun onResponse(
                    call: Call<AlbumApi.SearchResponse>?,
                    response: Response<AlbumApi.SearchResponse>
            ) {
                Log.d("album search", "response")
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("album search", response.body()!!.toString())
                    progress.postValue(SearchProgress.SUCCESS)
                    return
                }
                progress.postValue(SearchProgress.FAILED)
            }

            override fun onFailure(call: Call<AlbumApi.SearchResponse>?, t: Throwable?) {
                progress.postValue(SearchProgress.FAILED)
            }
        })
    }

    enum class SearchProgress {
        IN_PROGRESS, SUCCESS, FAILED
    }
}