package technopark.andruxa.myapplication.presentation.track

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.ImagesRepository
import technopark.andruxa.myapplication.TracksRepository
import technopark.andruxa.myapplication.models.Track

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
        val trackLiveData: LiveData<TracksRepository.ProgressSingle> =
            TracksRepository.getInstance(getApplication()).getTrack(name, artist)!!
        val similarTracksLiveData: LiveData<TracksRepository.Progress> =
            TracksRepository.getInstance(getApplication()).getSimilarTracks(name, artist)!!
        trackState.addSource(trackLiveData) { trackProgress ->
            Log.d("track", "callback")
            if (trackProgress.state === TracksRepository.ProgressSingle.State.SUCCESS) {
                Log.d("track", "success")
                trackState.value!!.track = trackProgress.track
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                }
                trackState.removeSource(trackLiveData)
            } else if (trackProgress.state === TracksRepository.ProgressSingle.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        trackState.postValue(TrackProgress(TrackProgress.State.FAILED))
                    } else {
                        trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                    }
                }
                trackState.removeSource(trackLiveData)
            }
        }
        trackState.addSource(similarTracksLiveData) { tracksProgress ->
            Log.d("track", "callback")
            if (tracksProgress.state === TracksRepository.Progress.State.SUCCESS) {
                Log.d("track", "success")
                trackState.value!!.similarTracks = tracksProgress.tracks
                ++responsesRecieved
                if (responsesRecieved == responsesAwaiting) {
                    trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                }
                trackState.removeSource(similarTracksLiveData)
            } else if (tracksProgress.state === TracksRepository.Progress.State.FAILED) {
                ++responsesRecieved
                ++responsesFailed
                if (responsesRecieved == responsesAwaiting) {
                    if (responsesFailed == responsesAwaiting) {
                        trackState.postValue(TrackProgress(TrackProgress.State.FAILED))
                    } else {
                        trackState.postValue(trackState.value!!.changeState(TrackProgress.State.SUCCESS))
                    }
                }
                trackState.removeSource(similarTracksLiveData)
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

    fun getImage(url: String): Bitmap? {
        return ImagesRepository.getInstance(getApplication()).getByUrl(url)
    }
}