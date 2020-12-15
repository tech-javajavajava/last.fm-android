package technopark.andruxa.myapplication.data.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.artist.ArtistSearchXML
import technopark.andruxa.myapplication.data.storages.lastFm.artist.ArtistXML
import technopark.andruxa.myapplication.data.storages.lastFm.artist.ArtistsTopXML
import technopark.andruxa.myapplication.data.storages.room.RoomStore
import technopark.andruxa.myapplication.models.artist.Artist

class ArtistRepo: IArtistRepo {

    override var artist: MutableLiveData<Artist> = MutableLiveData<Artist>()
        private set

    override var artists: MutableLiveData<List<Artist>> = MutableLiveData(List(0) { Artist() })
        private set

    private val lastFmStore = LastFmStore.instance.artistApi
    private val sqlStore = RoomStore.instance.artistRoomDao()

    override fun getByName(name: String, userName: String?): LiveData<Artist> {

        lastFmStore.getInfoByName(name).enqueue(object: Callback<ArtistXML> {
            override fun onResponse(call: Call<ArtistXML>, response: Response<ArtistXML>) {
                response.body()?.let { artist.postValue(it.toArtist()) }
//                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<ArtistXML>, t: Throwable) {
//                setState(IRepo.State.Err, "network error: '${t.message}'")
            }
        })

        artist.value?.name?.let {
            if (it == name) {
//                setState(IRepo.State.Ok, "same model requested")
                return artist
            }
        }

        artists.value?.let {
            val newArtist = it.find { artist -> artist.name == name}
            if (newArtist != null) {
                artist.postValue(newArtist)
//                setState(IRepo.State.Ok, "model found in cache")
                return artist
            }
        }

//        setState(IRepo.State.Warn, "looking in local storage")
        val storedArtist = sqlStore.getByName(name)
        if (storedArtist == null) {
//            setState(IRepo.State.Err, "not found in local storage")
        } else {
            artist.postValue(storedArtist.toArtist())
//            setState(IRepo.State.Ok, "found in local storage")
        }

        return artist
    }

    override fun getById(id: String, userName: String?): LiveData<Artist> {

        lastFmStore.getByMbid(id).enqueue(object: Callback<ArtistXML> {
            override fun onResponse(call: Call<ArtistXML>, response: Response<ArtistXML>) {
                response.body()?.let { artist.postValue(it.toArtist()) }
//                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<ArtistXML>, t: Throwable) {
//                setState(IRepo.State.Err, "network error: '${t.message}'")
            }
        })

        artist.value?.id?.let {
            if (it == id) {
//                setState(IRepo.State.Ok, "same model requested")
                return artist
            }
        }

        artists.value?.let {
            val newArtist = it.find { artist -> artist.id == id}
            if (newArtist != null) {
                artist.postValue(newArtist)
//                setState(IRepo.State.Ok, "model found in cache")
                return artist
            }
        }

//        setState(IRepo.State.Warn, "looking in local storage")
        val storedArtist = sqlStore.getByMbid(id)
        if (storedArtist == null) {
//            setState(IRepo.State.Err, "not found in local storage")
        } else {
            artist.postValue(storedArtist.toArtist())
//            setState(IRepo.State.Ok, "found in local storage")
        }

        return artist
    }

    override fun searchByName(name: String, limit: Int, page: Int): LiveData<List<Artist>> {
        lastFmStore.searchByName(name, limit, page).enqueue(object: Callback<ArtistSearchXML> {
            override fun onResponse(call: Call<ArtistSearchXML>, response: Response<ArtistSearchXML>) {
                response.body()?.let { artists.postValue(it.artists.map { artist -> artist.toArtist() }) }
//                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<ArtistSearchXML>, t: Throwable) {
//                setState(IRepo.State.Err, "network error: '${t.message}'")
            }
        })

        artists.value?.let {
            val newArtists = it.filter { album -> album.name == name }
            if (newArtists.isNotEmpty()) {
                artists.postValue(newArtists)
//                setState(IRepo.State.Ok, "search results found in cache")
                return artists
            }
        }

//        setState(IRepo.State.Warn, "looking in local storage")
        val storedArtists = sqlStore.searchByName(name, limit, page)
        if (storedArtists.isEmpty()) {
//            setState(IRepo.State.Err, "not found in local storage")
        } else {
            artists.postValue(storedArtists.map { artist -> artist.toArtist() })
//            setState(IRepo.State.Ok, "found in local storage")
        }

        return artists
    }

    override fun getTop(limit: Int, page: Int): LiveData<List<Artist>> {

        lastFmStore.getTop(page, limit).enqueue(object : Callback<ArtistsTopXML> {
            override fun onResponse(call: Call<ArtistsTopXML>, response: Response<ArtistsTopXML>) {
                response.body()?.let {
                    val newArtists =  it.artists.map { artist -> artist.toArtist() }
                    newArtists.forEach { artist -> artist.makeTop() }
                    artists.postValue(newArtists)
                }
//                setState(IRepo.State.Ok, "network response received")
            }

            override fun onFailure(call: Call<ArtistsTopXML>, t: Throwable) {
//                setState(IRepo.State.Err, "network error: '${t.message}'")
            }
        })

        artists.value?.let {
            val newArtists = it.filter { album -> album.isTop }
            if (newArtists.isNotEmpty()) {
                artists.postValue(newArtists)
//                setState(IRepo.State.Ok, "search results found in cache")
                return artists
            }
        }

//        setState(IRepo.State.Warn, "looking in local storage")
        val storedArtists = sqlStore.getTop(limit, (page - 1) * limit)
        if (storedArtists.isEmpty()) {
//            setState(IRepo.State.Err, "not found in local storage")
        } else {
            artists.postValue(storedArtists.map { artist -> artist.toArtist() })
//            setState(IRepo.State.Ok, "found in local storage")
        }

        return artists
    }
}