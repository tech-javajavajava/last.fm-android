package technopark.andruxa.myapplication.presentation.search

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.AlbumsRepository
import technopark.andruxa.myapplication.ArtistsRepository
import technopark.andruxa.myapplication.ImagesRepository
import technopark.andruxa.myapplication.TracksRepository
import technopark.andruxa.myapplication.models.Track
import technopark.andruxa.myapplication.network.AlbumApi
import technopark.andruxa.myapplication.network.ArtistApi

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
        val tracksSearchLiveData: LiveData<TracksRepository.Progress> =
                TracksRepository.getInstance(getApplication()).search(query, limit)!!
        val artistsSearchLiveData: LiveData<ArtistsRepository.Progress> =
                ArtistsRepository.getInstance(getApplication()).search(query, limit)!!
        val albumsSearchLiveData: LiveData<AlbumsRepository.SearchProgress> =
                AlbumsRepository.getInstance(getApplication()).search(query, limit)!!
        searchState.addSource(tracksSearchLiveData) { searchProgress ->
            Log.d("tracks search", "callback")
            if (searchProgress.state === TracksRepository.Progress.State.SUCCESS) {
                Log.d("tracks search", "success")
                searchState.value!!.tracks = searchProgress.tracks
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                }
                searchState.removeSource(tracksSearchLiveData)
            } else if (searchProgress.state === TracksRepository.Progress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        searchState.postValue(SearchProgress(SearchProgress.State.FAILED))
                    } else {
                        searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                    }
                }
                searchState.removeSource(tracksSearchLiveData)
            }
        }
        searchState.addSource(artistsSearchLiveData) { searchProgress ->
            Log.d("artists search", "callback")
            if (searchProgress.state === ArtistsRepository.Progress.State.SUCCESS) {
                Log.d("artists search", "success")
                searchState.value!!.artists = searchProgress.artists
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                }
                searchState.removeSource(artistsSearchLiveData)
            } else if (searchProgress.state === ArtistsRepository.Progress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        searchState.postValue(SearchProgress(SearchProgress.State.FAILED))
                    } else {
                        searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                    }
                }
                searchState.removeSource(artistsSearchLiveData)
            }
        }
        searchState.addSource(albumsSearchLiveData) { searchProgress ->
            Log.d("albums search", "callback")
            if (searchProgress.state === AlbumsRepository.SearchProgress.State.SUCCESS) {
                Log.d("albums search", "success")
                searchState.value!!.albums = searchProgress.albums
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                }
                searchState.removeSource(albumsSearchLiveData)
            } else if (searchProgress.state === AlbumsRepository.SearchProgress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        searchState.postValue(SearchProgress(SearchProgress.State.FAILED))
                    } else {
                        searchState.postValue(searchState.value!!.changeState(SearchProgress.State.SUCCESS))
                    }
                }
                searchState.removeSource(albumsSearchLiveData)
            }
        }
    }

    class SearchProgress(var state: State) {
        enum class State {
            NONE, ERROR, IN_PROGRESS, SUCCESS, FAILED
        }
        var tracks: List<Track>? = null
        var artists: List<ArtistApi.Artist>? = null
        var albums: List<AlbumApi.Album>? = null
        fun changeState(state: State): SearchProgress {
            this.state = state
            return this
        }
    }

    fun getImage(url: String): Bitmap? {
        return ImagesRepository.getInstance(getApplication()).getByUrl(url)
    }
}