package technopark.andruxa.myapplication.data.album

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumSearchXML
import technopark.andruxa.myapplication.data.storages.lastFm.album.AlbumXML
import technopark.andruxa.myapplication.data.storages.room.RoomStore
import technopark.andruxa.myapplication.models.album.Album

class AlbumRepo: IAlbumRepo {
    override val albumById: SData<Album> = SData()
    override val albumByNameNArtist: SData<Album> = SData()
    override val albumSearch: SData<List<Album>> = SData()

    private val sqlStore = RoomStore.instance.albumRoomDao()
    private val lastFmStore = LastFmStore.instance.albumApi

    override fun getById(id: String): SDataI<Album> {
        with(albumById) {

            postState(SDataI.State.Load)

            lastFmStore.getByMbid(id).enqueue(object: Callback<AlbumXML> {
                override fun onResponse(call: Call<AlbumXML>, response: Response<AlbumXML>) {
                    response.body()?.let { setData(it.toAlbum()) }
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<AlbumXML>, t: Throwable) {
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

//            val albumEntity = sqlStore.getById(id)
//            if (albumEntity != null) {
//                setMessage("${this.javaClass.name} sql hit")
//                setData(albumEntity.toAlbum())
//                postState(SDataI.State.SqlOk)
//                return this
//            }

            return this
        }
    }

    override fun getByNameNArtist(name: String, artistName: String, userName: String?): SDataI<Album> {

        with(albumByNameNArtist) {

            postState(SDataI.State.Load)

            lastFmStore.getByNameNArtist(name, artistName, 1, userName).enqueue(object: Callback<AlbumXML> {
                override fun onResponse(call: Call<AlbumXML>, response: Response<AlbumXML>) {
                    response.body()?.let { setData(it.toAlbum()) }
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<AlbumXML>, t: Throwable) {
                    setMessage("network error: '${t.message}'")
                    setNetErr(true)
                    postState(SDataI.State.Err)
                }
            })

            if (data?.name == name && data?.artistName == artistName) {
                setMessage("${this.javaClass.name} cache hit")
                postState(SDataI.State.CacheOk)
                return this
            }

//            val albumEntity = sqlStore.getByNameNArtist(name, artistName)
//            if (albumEntity != null) {
//                setMessage("${this.javaClass.name} sql hit")
//                setData(albumEntity.toAlbum())
//                postState(SDataI.State.SqlOk)
//                return this
//            }

            return this
        }
    }

    override fun searchByName(name: String, limit: Int, page: Int): SDataI<List<Album>> {
        with(albumSearch) {

            postState(SDataI.State.Load)

            lastFmStore.searchByName(name).enqueue(object: Callback<AlbumSearchXML> {
                override fun onResponse(call: Call<AlbumSearchXML>, response: Response<AlbumSearchXML>) {
                    setData(response.body()?.albums?.map { albumXML -> albumXML.toAlbum() })
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<AlbumSearchXML>, t: Throwable) {
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

//            val albumEntities = sqlStore.search(name, limit, (page - 1) * limit)
//            if (albumEntities.isNotEmpty()) {
//                setMessage("${this.javaClass.name} sql hit")
//                setData(albumEntities.map { albumEntity -> albumEntity.toAlbum() })
//                postState(SDataI.State.SqlOk)
//                return this
//            }

            return this
        }
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
