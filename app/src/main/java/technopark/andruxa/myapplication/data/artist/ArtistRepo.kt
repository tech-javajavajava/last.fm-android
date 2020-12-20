package technopark.andruxa.myapplication.data.artist

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.artist.*
import technopark.andruxa.myapplication.data.storages.room.RoomStore
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

class ArtistRepo: IArtistRepo {

    override var artistById: SData<Artist> = SData()
        private set
    override var artistByName: SData<Artist> = SData()
        private set
    override var artistSearch: SData<List<Artist>> = SData()
        private set
    override var artistTop: SData<List<Artist>> = SData()
        private set
    override var artistTopAlbums: SData<List<Album>> = SData()
        private set
    override var artistTopTracks: SData<List<Track>> = SData()
        private set
    override var artistTopTags: SData<List<Tag>> = SData()
        private set

    private val lastFmStore = LastFmStore.instance.artistApi
    private val sqlStore = RoomStore.instance.artistRoomDao()

    override fun getById(id: String, userName: String?): SDataI<Artist> {
        with(artistById) {

            postState(SDataI.State.Load)

            lastFmStore.getByMbid(id).enqueue(object: Callback<ArtistInfoXML> {
                override fun onResponse(call: Call<ArtistInfoXML>, response: Response<ArtistInfoXML>) {
                    response.body()?.let { setData(it.artist?.toArtist()) }
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<ArtistInfoXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            if (data?.id == id) {
                setMessage("${this.javaClass.name} cache hit")
                postState(SDataI.State.CacheOk)
                return this
            }

//            val albumEntity = sqlStore.getByMbid(id)
//            if (albumEntity != null) {
//                setMessage("${this.javaClass.name} sql hit")
//                setData(albumEntity.toArtist())
//                postState(SDataI.State.SqlOk)
//                return this
//            }

            return this
        }
    }

    override fun getByName(name: String, userName: String?): SDataI<Artist> {
        with(artistByName) {

            postState(SDataI.State.Load)

            lastFmStore.getInfoByName(name, 1, userName).enqueue(object: Callback<ArtistInfoXML> {
                override fun onResponse(call: Call<ArtistInfoXML>, response: Response<ArtistInfoXML>) {
                    response.body()?.let { setData(it.artist?.toArtist()) }
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<ArtistInfoXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            if (data?.name == name) {
                setMessage("${this.javaClass.name} cache hit")
                postState(SDataI.State.CacheOk)
                return this
            }

//            val albumEntity = sqlStore.getByName(name)
//            if (albumEntity != null) {
//                setMessage("${this.javaClass.name} sql hit")
//                setData(albumEntity.toArtist())
//                postState(SDataI.State.SqlOk)
//                return this
//            }

            return this
        }
    }

    override fun searchByName(name: String, limit: Int, page: Int): SDataI<List<Artist>> {
        with(artistSearch) {

            postState(SDataI.State.Load)

            lastFmStore.searchByName(name, page, limit).enqueue(object: Callback<ArtistSearchXML> {
                override fun onResponse(call: Call<ArtistSearchXML>, response: Response<ArtistSearchXML>) {
                    setData(response.body()?.artists?.map { albumXML -> albumXML.toArtist() })
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<ArtistSearchXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            val cacheResult = data?.filter { album -> album.name == name }
            if (cacheResult != null && cacheResult.isNotEmpty()) {
                setMessage("${this.javaClass.name} cache hit")
                postState(SDataI.State.CacheOk)
                return this
            }

//            val artistEntites = sqlStore.searchByName(name, limit, (page - 1) * limit)
//            if (artistEntites.isNotEmpty()) {
//                setMessage("${this.javaClass.name} sql hit")
//                postState(SDataI.State.SqlOk)
//                setData(artistEntites.map { artistEntity-> artistEntity.toArtist() })
//                return this
//            }

            return this
        }
    }

    override fun getTop(limit: Int, page: Int): SDataI<List<Artist>> {

        with(artistTop) {
            postState(SDataI.State.Load)
            lastFmStore.getTop(page, limit).enqueue(object : Callback<ArtistsTopXML> {
                override fun onResponse(call: Call<ArtistsTopXML>, response: Response<ArtistsTopXML>) {
                    setData(response.body()?.artists?.map { a -> a.toArtist() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<ArtistsTopXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            if (!data.isNullOrEmpty()) {
                setMessage("${this.javaClass.name} cache hit")
                postState(SDataI.State.CacheOk)
                return this
            }

//            val storedArtists = sqlStore.getTop(limit, (page - 1) * limit)
//            if (storedArtists.isNotEmpty()) {
//                setData(storedArtists.map { a -> a.toArtist() })
//                setMessage("${this.javaClass.name} sql hit")
//                postState(SDataI.State.SqlOk)
//                return this
//            }

            return this
        }
    }

    override fun getTopAlbums(name: String, limit: Int, page: Int): SDataI<List<Album>> {
        with(artistTopAlbums) {
            postState(SDataI.State.Load)
            lastFmStore.getTopAlbums(name, limit, page).enqueue(object : Callback<ArtistTopAlbumsXML> {
                override fun onResponse(call: Call<ArtistTopAlbumsXML>, response: Response<ArtistTopAlbumsXML>) {
                    setData(response.body()?.albums?.map { a -> a.toAlbum() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<ArtistTopAlbumsXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            return this
        }
    }

    override fun getTopTags(name: String): SDataI<List<Tag>> {
        with(artistTopTags) {
            postState(SDataI.State.Load)
            lastFmStore.getTopTags(name).enqueue(object : Callback<ArtistTopTagsXML> {
                override fun onResponse(call: Call<ArtistTopTagsXML>, response: Response<ArtistTopTagsXML>) {
                    setData(response.body()?.tags?.map { a -> a.toTag() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<ArtistTopTagsXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            return this
        }
    }

    override fun getTopTracks(name: String, limit: Int, page: Int): SDataI<List<Track>> {
        with(artistTopTracks) {
            postState(SDataI.State.Load)
            lastFmStore.getTopTracks(name, limit, page).enqueue(object : Callback<ArtistTopTracksXML> {
                override fun onResponse(call: Call<ArtistTopTracksXML>, response: Response<ArtistTopTracksXML>) {
                    setData(response.body()?.tracks?.map { a -> a.toTrack() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<ArtistTopTracksXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            return this
        }
    }

    companion object {
        var repo: IArtistRepo? = null
        fun getInstance(): IArtistRepo {
            if (repo == null) {
                repo = ArtistRepo()
            }
            return repo as IArtistRepo
        }
    }
}