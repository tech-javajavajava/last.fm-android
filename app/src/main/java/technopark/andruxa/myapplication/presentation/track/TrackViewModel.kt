package technopark.andruxa.myapplication.presentation.track

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.models.track.Track

class TrackViewModel(application: Application) : AndroidViewModel(application) {
    private val trackState = MediatorLiveData<TrackProgress>()

    init {
        trackState.value = TrackProgress(TrackProgress.State.NONE)
    }

    fun getTrackState(): LiveData<TrackProgress> {
        return trackState
    }

    fun getTrack(name: String, artist: String) {
        if (trackState.value!!.state != TrackProgress.State.IN_PROGRESS) {
            requestTrack(name, artist)
        }
    }

    private fun requestTrack(name: String, artist: String) {
        Log.d("track", "request")
        trackState.postValue(TrackProgress(TrackProgress.State.IN_PROGRESS))
        val responsesAwaiting = 2
        var responsesRecieved = 0
        var responsesFailed = 0
        trackState.value?.track = null
        trackState.value?.similarTracks = null
        val trackLiveData = TrackRepo.getInstance().getByNameNArtist(name, artist)
        val similarTracksLiveData = TrackRepo.getInstance().getSimilar(name, artist)
        trackState.addSource(trackLiveData.state) { trackProgress ->
            Log.d("track", "callback")
            if (trackLiveData.isOk()) {
                Log.d("track", "success")
                trackState.value!!.track = trackLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                }
                trackState.removeSource(trackLiveData.state)
            } else if (trackProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        trackState.postValue(TrackProgress(TrackProgress.State.FAILED))
                    } else {
                        trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                    }
                }
                trackState.removeSource(trackLiveData.state)
            }
        }
        trackState.addSource(similarTracksLiveData.state) { tracksProgress ->
            Log.d("track", "callback")
            if (similarTracksLiveData.isOk()) {
                Log.d("track", "success")
                trackState.value!!.similarTracks = similarTracksLiveData.data
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                }
                trackState.removeSource(similarTracksLiveData.state)
            } else if (tracksProgress === SDataI.State.Err) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        trackState.postValue(TrackProgress(TrackProgress.State.FAILED))
                    } else {
                        trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                    }
                }
                trackState.removeSource(similarTracksLiveData.state)
            }
        }
    }

    class TrackProgress(var state: State) {
        enum class State {
            NONE, IN_PROGRESS, SUCCESS, FAILED
        }
        var track: Track? = null
        var similarTracks: List<Track>? = null
        fun changeState(state: State): TrackProgress {
            this.state = state
            return this
        }
    }
}