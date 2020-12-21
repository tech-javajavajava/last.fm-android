package technopark.andruxa.myapplication.presentation.track

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.album.AlbumRepo
import technopark.andruxa.myapplication.models.album.Album

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    private val albumState = MediatorLiveData<AlbumProgress>()

    init {
        albumState.value = AlbumProgress(AlbumProgress.State.NONE)
    }

    fun getAlbumState(): LiveData<AlbumProgress> {
        return albumState
    }

    fun getAlbum(name: String, artist: String) {
        if (albumState.value!!.state == AlbumProgress.State.IN_PROGRESS) {
            return
        }
        Log.d("album", "request")
        albumState.postValue(AlbumProgress(AlbumProgress.State.IN_PROGRESS))
        albumState.value?.album = null
        val albumLiveData = AlbumRepo.getInstance().getByNameNArtist(name, artist)
        albumState.addSource(albumLiveData.state) { albumProgress ->
            Log.d("album", "callback")
            if (albumLiveData.isOk()) {
                Log.d("album", "success")
                albumState.value!!.album = albumLiveData.data
                Log.d("album", albumLiveData.data.toString());
                albumState.postValue(albumState.value!!.changeState(AlbumProgress.State.SUCCESS))
                albumState.removeSource(albumLiveData.state)
            } else if (albumProgress === SDataI.State.Err) {
                albumState.postValue(AlbumProgress(AlbumProgress.State.FAILED))
                albumState.removeSource(albumLiveData.state)
            }
        }
    }

    class AlbumProgress(var state: State) {
        enum class State {
            NONE, IN_PROGRESS, SUCCESS, FAILED
        }
        var album: Album? = null
        fun changeState(state: State): AlbumProgress {
            this.state = state
            return this
        }
    }
}