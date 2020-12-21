package technopark.andruxa.myapplication.presentation.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.artist.ArtistRepo
import technopark.andruxa.myapplication.data.tag.TagRepo
import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val chartsState = MediatorLiveData<ChartsProgress>()

    init {
        chartsState.value = ChartsProgress(ChartsProgress.State.NONE)
    }

    fun getChartsState(): LiveData<ChartsProgress> {
        return chartsState
    }

    fun getCharts(short: Boolean) {
        if (chartsState.value!!.state != ChartsProgress.State.IN_PROGRESS) {
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
        val tracksChartLiveData = TrackRepo.getInstance().getTop(3)
        val artistsChartLiveData = ArtistRepo.getInstance().getTop(3)
        val tagsChartLiveData = TagRepo.getInstance().getTop(3)
        chartsState.addSource(tracksChartLiveData.state) { chartProgress ->
            Log.d("tracks chart", "callback")
            if (tracksChartLiveData.isOk()) {
                Log.d("tracks chart", "success")
                chartsState.value!!.tracks = tracksChartLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                }
                chartsState.removeSource(tracksChartLiveData.state)
            } else if (chartProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        chartsState.postValue(ChartsProgress(ChartsProgress.State.FAILED))
                    } else {
                        chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                    }
                }
                chartsState.removeSource(tracksChartLiveData.state)
            }
        }
        chartsState.addSource(artistsChartLiveData.state) { chartProgress ->
            Log.d("artists chart", "callback")
            if (artistsChartLiveData.isOk()) {
                Log.d("artists chart", "success")
                chartsState.value!!.artists = artistsChartLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                }
                chartsState.removeSource(artistsChartLiveData.state)
            } else if (chartProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        chartsState.postValue(ChartsProgress(ChartsProgress.State.FAILED))
                    } else {
                        chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                    }
                }
                chartsState.removeSource(artistsChartLiveData.state)
            }
        }
        chartsState.addSource(tagsChartLiveData.state) { chartProgress ->
            Log.d("tags chart", "callback")
            if (tagsChartLiveData.isOk()) {
                Log.d("tags chart", "success")
                chartsState.value!!.tags = tagsChartLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                }
                chartsState.removeSource(tagsChartLiveData.state)
            } else if (chartProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        chartsState.postValue(ChartsProgress(ChartsProgress.State.FAILED))
                    } else {
                        chartsState.postValue(chartsState.value!!.changeState(ChartsProgress.State.SUCCESS))
                    }
                }
                chartsState.removeSource(tagsChartLiveData.state)
            }
        }
    }

    class ChartsProgress(var state: State) {
        enum class State {
            NONE, IN_PROGRESS, SUCCESS, FAILED
        }
        var tracks: List<Track>? = null
        var artists: List<Artist>? = null
        var tags: List<Tag>? = null
        fun changeState(state: State): ChartsProgress {
            this.state = state
            return this
        }
    }

    fun getTagImageUrl(tag: String): SDataI<List<Artist>> {
        return TagRepo.getInstance().getTopArtists(tag, limit = 1)
    }
}