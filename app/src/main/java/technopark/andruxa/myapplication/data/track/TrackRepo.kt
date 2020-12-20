package technopark.andruxa.myapplication.data.track

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackSearchXML
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackTopXML
import technopark.andruxa.myapplication.data.storages.lastFm.track.TrackXML
import technopark.andruxa.myapplication.models.track.Track

class TrackRepo: ITrackRepo {

    override var trackById: SData<Track> = SData()
        private set
    override var trackByNameNArtist: SData<Track> = SData()
        private set
    override var trackSearched: SData<List<Track>> = SData()
        private set
    override var trackTop: SData<List<Track>> = SData()
        private set

    private val lastFmStore = LastFmStore.instance.trackApi

    override fun getById(id: String, userName: String?): SDataI<Track> {
        with(trackById) {

            postState(SDataI.State.Load)

            lastFmStore.getByMbid(id).enqueue(object: Callback<TrackXML> {
                override fun onResponse(call: Call<TrackXML>, response: Response<TrackXML>) {
                    response.body()?.let { setData(it.toTrack()) }
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<TrackXML>, t: Throwable) {
                    networkError(t.message)
                }
            })

            return this
        }
    }

    override fun getByNameNArtist(name: String, artistName: String, userName: String?): SDataI<Track> {
        with(trackByNameNArtist) {

            postState(SDataI.State.Load)

            lastFmStore.getByNameNArtist(name, artistName, 1, userName).enqueue(object: Callback<TrackXML> {
                override fun onResponse(call: Call<TrackXML>, response: Response<TrackXML>) {
                    response.body()?.let { setData(it.toTrack()) }
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<TrackXML>, t: Throwable) {
                    networkError(t.message)
                }
            })

            return this
        }
    }

    override fun searchByName(
        name: String,
        artistName: String?,
        limit: Int,
        page: Int
    ): SDataI<List<Track>> {
        with(trackSearched) {

            postState(SDataI.State.Load)

            lastFmStore.searchByName(name).enqueue(object: Callback<TrackSearchXML> {
                override fun onResponse(call: Call<TrackSearchXML>, response: Response<TrackSearchXML>) {
                    setData(response.body()?.tracks?.map { t -> t.toTrack()})
                    setNetErr(false)
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<TrackSearchXML>, t: Throwable) {
                    networkError(t.message)
                }
            })

            return this
        }
    }

    override fun getTop(limit: Int, page: Int): SDataI<List<Track>> {
        with(trackTop) {
            postState(SDataI.State.Load)
            lastFmStore.getTop(page, limit).enqueue(object : Callback<TrackTopXML> {
                override fun onResponse(call: Call<TrackTopXML>, response: Response<TrackTopXML>) {
                    setData(response.body()?.tracks?.map { t -> t.toTrack() })
                    postState(SDataI.State.NetOk)
                    setNetErr(false)
                }

                override fun onFailure(call: Call<TrackTopXML>, t: Throwable) {
                    networkError(t.message)
                }
            })

            return this
        }
    }

    companion object {
        var repo: ITrackRepo? = null
        fun getInstance(): ITrackRepo {
            if (repo == null) {
                repo = TrackRepo()
            }
            return repo as ITrackRepo
        }
    }
}