package technopark.andruxa.myapplication.data.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.IRepo
import technopark.andruxa.myapplication.data.RepoState
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumSearchXML
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumXML
import technopark.andruxa.myapplication.data.storages.room.RoomStore
import technopark.andruxa.myapplication.models.album.Album

class AlbumRepo: RepoState(), IAlbumRepo {
    override val album: MutableLiveData<Album> = MutableLiveData(Album())
    override val albums: MutableLiveData<List<Album>> = MutableLiveData(List(0) { Album() })

    private val sqlStore = RoomStore.instance.albumRoomDao()
    private val lastFmStore = LastFmStore.instance.albumApi

    override fun getById(id: String): LiveData<Album> {
        lastFmStore.getByMbid(id).enqueue(object: Callback<AlbumXML> {
            override fun onResponse(call: Call<AlbumXML>, response: Response<AlbumXML>) {
                response.body()?.let { album.postValue(it.toAlbum()) }
                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<AlbumXML>, t: Throwable) {
                setState(IRepo.State.Err, "network error: '${t.message}'")
            }
        })

        album.value?.id?.let {
            if (it == id) {
                setState(IRepo.State.Ok, "same model requested")
                return album
            }
        }

        albums.value?.let {
            val newAlbum = it.find { album -> album.id == id}
            if (newAlbum != null) {
                album.postValue(newAlbum)
                setState(IRepo.State.Ok, "model found in cache")
                return album
            }
        }

        setState(IRepo.State.Warn, "looking in local storage")
        val storedAlbum = sqlStore.getById(id)
        if (storedAlbum == null) {
            setState(IRepo.State.Err, "not found in local storage")
        } else {
            album.postValue(storedAlbum.toAlbum())
            setState(IRepo.State.Ok, "found in local storage")
        }

        return album
    }

    override fun getByNameNArtist(name: String, artistName: String, userName: String?): LiveData<Album> {

        lastFmStore.getByNameNArtist(name, artistName).enqueue(object: Callback<AlbumXML> {
            override fun onResponse(call: Call<AlbumXML>, response: Response<AlbumXML>) {
                response.body()?.let { album.postValue(it.toAlbum()) }
                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<AlbumXML>, t: Throwable) {
                setState(IRepo.State.Err, "network error: '${t.message}'")
            }
        })

        album.value?.let {
            if (it.name != null && it.artistName != null &&
                    it.name == name && it.artistName == artistName) {
                setState(IRepo.State.Ok, "same model requested")
                return album
            }
        }

        albums.value?.let {
            val newAlbum = it.find { album -> album.name == name && album.artistName == artistName}
            if (newAlbum != null) {
                album.postValue(newAlbum)
                setState(IRepo.State.Ok, "model found in cache")
                return album
            }
        }

        setState(IRepo.State.Warn, "looking in local storage")
        val storedAlbum = sqlStore.getByNameNArtist(name, artistName)
        if (storedAlbum == null) {
            setState(IRepo.State.Err, "not found in local storage")
        } else {
            album.postValue(storedAlbum.toAlbum())
            setState(IRepo.State.Ok, "found in local storage")
        }

        return album
    }

    override fun searchByName(name: String, limit: Int, page: Int): LiveData<List<Album>> {
        lastFmStore.searchByName(name, limit, page).enqueue(object: Callback<AlbumSearchXML> {
            override fun onResponse(
                call: Call<AlbumSearchXML>,
                response: Response<AlbumSearchXML>
            ) {
                response.body()?.let { albums.postValue(it.albums.map { albumXML -> albumXML.toAlbum() }) }
                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<AlbumSearchXML>, t: Throwable) {
                setState(IRepo.State.Err, "network error: ${t.message}")
            }
        })

        albums.value?.let {
            val newAlbums = it.filter { album -> album.name == name }
            if (newAlbums.isNotEmpty()) {
                albums.postValue(newAlbums)
                setState(IRepo.State.Ok, "search results found in cache")
                return albums
            }
        }

        val newAlbumList = sqlStore.search(name, limit, (page - 1) * limit)
        if (newAlbumList.isEmpty()) {
            setState(IRepo.State.Err, "not found in local storage")
        } else {
            setState(IRepo.State.Ok, "found in local storage")
            albums.postValue(newAlbumList.map { albumEntity -> albumEntity.toAlbum() })
        }

        return albums
    }

    companion object {
        var repo: IAlbumRepo? = null
        fun getInstance(): IAlbumRepo {
            if (repo == null) {
                repo = AlbumRepo()
            }
            return repo as IAlbumRepo
        }
    }
}
