package technopark.andruxa.myapplication.presentation.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.album.AlbumRepo
import technopark.andruxa.myapplication.data.artist.ArtistRepo
import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.track.Track

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private var lastQuery: String? = null
    private val searchState = MediatorLiveData<SearchProgress>()

    init {
        searchState.value = SearchProgress(SearchProgress.State.NONE)
    }

    fun getSearchState(): LiveData<SearchProgress> {
        return searchState
    }

    fun search(query: String?, limit: Int) {
        val last = lastQuery
        lastQuery = query
        if (query == null || query == "") {
            searchState.postValue(SearchProgress(SearchProgress.State.ERROR))
        } else if (searchState.value!!.state != SearchProgress.State.IN_PROGRESS && last != query) {
            requestSearch(query, limit)
        }
    }

    private fun requestSearch(query: String, limit: Int) {
        Log.d("search", "request")
        searchState.postValue(SearchProgress(SearchProgress.State.IN_PROGRESS))
        val responsesAwaiting = 3
        var responsesRecieved = 0
        var responsesFailed = 0
        searchState.value?.tracks = null
        searchState.value?.artists = null
        searchState.value?.albums = null
        val tracksSearchLiveData = TrackRepo.getInstance().searchByName(query, null, limit)
        val artistsSearchLiveData = ArtistRepo.getInstance().searchByName(query, limit)
        val albumsSearchLiveData = AlbumRepo.getInstance().searchByName(query, limit)
        searchState.addSource(tracksSearchLiveData.state) { searchProgress ->
            Log.d("tracks search", "callback")
            if (tracksSearchLiveData.isOk()) {
                Log.d("tracks search", "success")
                searchState.value!!.tracks = tracksSearchLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                }
                searchState.removeSource(tracksSearchLiveData.state)
            } else if (searchProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        searchState.postValue(SearchProgress(SearchProgress.State.FAILED))
                    } else {
                        searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                    }
                }
                searchState.removeSource(tracksSearchLiveData.state)
            }
        }
        searchState.addSource(artistsSearchLiveData.state) { searchProgress ->
            Log.d("artists search", "callback")
            if (artistsSearchLiveData.isOk()) {
                Log.d("artists search", "success")
                searchState.value!!.artists = artistsSearchLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                }
                searchState.removeSource(artistsSearchLiveData.state)
            } else if (searchProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        searchState.postValue(SearchProgress(SearchProgress.State.FAILED))
                    } else {
                        searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                    }
                }
                searchState.removeSource(artistsSearchLiveData.state)
            }
        }
        searchState.addSource(albumsSearchLiveData.state) { searchProgress ->
            Log.d("albums search", "callback")
            if (albumsSearchLiveData.isOk()) {
                Log.d("albums search", "success")
                searchState.value!!.albums = albumsSearchLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                }
                searchState.removeSource(albumsSearchLiveData.state)
            } else if (searchProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        searchState.postValue(SearchProgress(SearchProgress.State.FAILED))
                    } else {
                        searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                    }
                }
                searchState.removeSource(albumsSearchLiveData.state)
            }
        }
    }

    class SearchProgress(var state: State) {
        enum class State {
            NONE, ERROR, IN_PROGRESS, SUCCESS, FAILED
        }
        var tracks: List<Track>? = null
        var artists: List<Artist>? = null
        var albums: List<Album>? = null
        fun changeState(state: State): SearchProgress {
            this.state = state
            return this
        }
    }
}