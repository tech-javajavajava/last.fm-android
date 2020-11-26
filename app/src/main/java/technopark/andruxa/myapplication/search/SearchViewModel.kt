package technopark.andruxa.myapplication.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.AlbumsRepository
import technopark.andruxa.myapplication.ArtistsRepository
import technopark.andruxa.myapplication.TracksRepository
import technopark.andruxa.myapplication.network.TrackApi

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private var lastQuery: String? = null
    private val searchState = MediatorLiveData<SearchProgress>()
    var tracks: Array<TrackApi.Track>? = null
    var artists: Array<TrackApi.Track>? = null
    var albums: Array<TrackApi.Track>? = null

    init {
        searchState.value = SearchProgress.NONE
    }

    fun getSearchState(): LiveData<SearchProgress> {
        return searchState
    }

    fun search(query: String?, limit: Int) {
        val last = lastQuery
        lastQuery = query
        if (query == null || query == "") {
            searchState.postValue(SearchProgress.ERROR)
        } else if (searchState.value != SearchProgress.IN_PROGRESS && last != query) {
            requestSearch(query, limit)
        }
    }

    private fun requestSearch(query: String, limit: Int) {
        Log.d("search", "request")
        searchState.postValue(SearchProgress.IN_PROGRESS)
        val tracksSearchLiveData: LiveData<TracksRepository.SearchProgress> =
                TracksRepository.getInstance(getApplication()).search(query, limit)!!
        val artistsSearchLiveData: LiveData<ArtistsRepository.SearchProgress> =
                ArtistsRepository.getInstance(getApplication()).search(query, limit)!!
        val albumsSearchLiveData: LiveData<AlbumsRepository.SearchProgress> =
                AlbumsRepository.getInstance(getApplication()).search(query, limit)!!
        searchState.addSource(tracksSearchLiveData) { searchProgress ->
            Log.d("tracks search", "callback")
            if (searchProgress === TracksRepository.SearchProgress.SUCCESS) {
                Log.d("tracks search", "success")
                tracks = TracksRepository.getInstance(getApplication()).tracks
                searchState.postValue(SearchProgress.TRACKS_SUCCESS)
                searchState.removeSource(tracksSearchLiveData)
            } else if (searchProgress === TracksRepository.SearchProgress.FAILED) {
                searchState.postValue(SearchProgress.TRACKS_FAILED)
                searchState.removeSource(tracksSearchLiveData)
            }
        }
        searchState.addSource(artistsSearchLiveData) { searchProgress ->
            Log.d("artists search", "callback")
            if (searchProgress === ArtistsRepository.SearchProgress.SUCCESS) {
                Log.d("artists search", "success")
                searchState.postValue(SearchProgress.ARTISTS_SUCCESS)
                searchState.removeSource(artistsSearchLiveData)
            } else if (searchProgress === ArtistsRepository.SearchProgress.FAILED) {
                searchState.postValue(SearchProgress.ARTISTS_FAILED)
                searchState.removeSource(artistsSearchLiveData)
            }
        }
        searchState.addSource(albumsSearchLiveData) { searchProgress ->
            Log.d("albums search", "callback")
            if (searchProgress === AlbumsRepository.SearchProgress.SUCCESS) {
                Log.d("albums search", "success")
                searchState.postValue(SearchProgress.ALBUMS_SUCCESS)
                searchState.removeSource(albumsSearchLiveData)
            } else if (searchProgress === AlbumsRepository.SearchProgress.FAILED) {
                searchState.postValue(SearchProgress.ALBUMS_FAILED)
                searchState.removeSource(albumsSearchLiveData)
            }
        }
    }

    enum class SearchProgress {
        NONE, ERROR, IN_PROGRESS,
        TRACKS_SUCCESS, ARTISTS_SUCCESS, ALBUMS_SUCCESS,
        TRACKS_FAILED, ARTISTS_FAILED, ALBUMS_FAILED
    }
}