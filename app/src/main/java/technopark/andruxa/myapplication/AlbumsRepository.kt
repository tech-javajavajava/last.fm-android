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
        Log.d("albums search", "repository search 2")
        val api: AlbumApi? = api?.albumApi
        api?.search(query, limit)?.enqueue(object : Callback<AlbumApi.AlbumSearchResponse> {
            override fun onResponse(
                    call: Call<AlbumApi.AlbumSearchResponse>?,
                    response: Response<AlbumApi.AlbumSearchResponse>
            ) {
                Log.d("album search", "response")
                response.body()?.let {
                    if (response.isSuccessful) {
                        Log.d("album search", it.toString())
                        progress.postValue(SearchProgress(SearchProgress.State.SUCCESS, it.results!!.albums))
                        return
                    }
                }
                progress.postValue(SearchProgress(SearchProgress.State.FAILED))
            }

            override fun onFailure(call: Call<AlbumApi.AlbumSearchResponse>?, t: Throwable?) {
                progress.postValue(SearchProgress(SearchProgress.State.FAILED))
            }
        })
    }

    class SearchProgress(var state: State) {
        constructor(state: State, albums: List<AlbumApi.Album>?): this(state) {
            this.albums = albums
        }
        enum class State {
            IN_PROGRESS, SUCCESS, FAILED
        }
        var albums: List<AlbumApi.Album>? = null
    }
}