package technopark.andruxa.myapplication.home

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.ArtistsRepository
import technopark.andruxa.myapplication.ImagesRepository
import technopark.andruxa.myapplication.TagsRepository
import technopark.andruxa.myapplication.TracksRepository
import technopark.andruxa.myapplication.network.ArtistApi
import technopark.andruxa.myapplication.network.TagApi
import technopark.andruxa.myapplication.network.TrackApi

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val chartsState = MediatorLiveData<HomeViewModel.ChartsProgress>()

    init {
        chartsState.value = HomeViewModel.ChartsProgress(HomeViewModel.ChartsProgress.State.NONE)
    }

    fun getChartsState(): LiveData<HomeViewModel.ChartsProgress> {
        return chartsState
    }

    fun getCharts(short: Boolean) {
        if (chartsState.value!!.state != HomeViewModel.ChartsProgress.State.IN_PROGRESS) {
            requestCharts(short)
        }
    }

    private fun requestCharts(short: Boolean) {
        Log.d("charts", "request")
        chartsState.postValue(ChartsProgress(ChartsProgress.State.IN_PROGRESS))
        val responsesAwaiting = 3
        var responsesRecieved = 0
        var responsesFailed = 0
        chartsState.value?.tracks = null
        chartsState.value?.artists = null
        chartsState.value?.tags = null
        val tracksChartLiveData: LiveData<TracksRepository.Progress> =
            TracksRepository.getInstance(getApplication()).getChart(3)!!
        val artistsChartLiveData: LiveData<ArtistsRepository.Progress> =
            ArtistsRepository.getInstance(getApplication()).getChart(3)!!
        val tagsChartLiveData: LiveData<TagsRepository.Progress> =
            TagsRepository.getInstance(getApplication()).getChart(9)!!
        chartsState.addSource(tracksChartLiveData) { chartProgress ->
            Log.d("tracks chart", "callback")
            if (chartProgress.state === TracksRepository.Progress.State.SUCCESS) {
                Log.d("tracks chart", "success")
                chartsState.value!!.tracks = chartProgress.tracks
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                }
                chartsState.removeSource(tracksChartLiveData)
            } else if (chartProgress.state === TracksRepository.Progress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        chartsState.postValue(ChartsProgress(ChartsProgress.State.FAILED))
                    } else {
                        chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                    }
                }
                chartsState.removeSource(tracksChartLiveData)
            }
        }
        chartsState.addSource(artistsChartLiveData) { chartProgress ->
            Log.d("artists chart", "callback")
            if (chartProgress.state === ArtistsRepository.Progress.State.SUCCESS) {
                Log.d("artists chart", "success")
                chartsState.value!!.artists = chartProgress.artists
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                }
                chartsState.removeSource(artistsChartLiveData)
            } else if (chartProgress.state === ArtistsRepository.Progress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        chartsState.postValue(ChartsProgress(ChartsProgress.State.FAILED))
                    } else {
                        chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                    }
                }
                chartsState.removeSource(artistsChartLiveData)
            }
        }
        chartsState.addSource(tagsChartLiveData) { chartProgress ->
            Log.d("tags chart", "callback")
            if (chartProgress.state === TagsRepository.Progress.State.SUCCESS) {
                Log.d("tags chart", "success")
                chartsState.value!!.tags = chartProgress.tags
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                }
                chartsState.removeSource(tagsChartLiveData)
            } else if (chartProgress.state === TagsRepository.Progress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        chartsState.postValue(ChartsProgress(ChartsProgress.State.FAILED))
                    } else {
                        chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                    }
                }
                chartsState.removeSource(tagsChartLiveData)
            }
        }
    }

    class ChartsProgress(var state: State) {
        enum class State {
            NONE, IN_PROGRESS, SUCCESS, FAILED
        }
        var tracks: List<TrackApi.Track>? = null
        var artists: List<ArtistApi.Artist>? = null
        var tags: List<TagApi.Tag>? = null
        fun changeState(state: State): ChartsProgress {
            this.state = state
            return this
        }
    }

    fun getImage(url: String): Bitmap {
        return ImagesRepository.getInstance(getApplication()).getByUrl(url)
    }

    fun getTagImageUrl(tag: String): LiveData<String?> {
        return TagsRepository.getInstance(getApplication()).getImageUrl(tag)
    }
}