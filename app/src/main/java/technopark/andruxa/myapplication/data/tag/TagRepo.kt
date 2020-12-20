package technopark.andruxa.myapplication.data.tag

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.tag.*
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

class TagRepo: ITagRepo {
    override var tagByName: SData<Tag> = SData()
        private set
    override var tagTop: SData<List<Tag>> = SData()
        private set
    override var tagSimilar: SData<List<Tag>> = SData()
        private set
    override var tagTopAlbums: SData<List<Album>> = SData()
        private set
    override var tagTopArtists: SData<List<Artist>> = SData()
        private set
    override var tagTopTags: SData<List<Tag>> = SData()
        private set
    override var tagTopTracks: SData<List<Track>> = SData()
        private set

    private val lastFmStore = LastFmStore.instance.tagsApi

    override fun getByName(name: String): SDataI<Tag> {
        tagByName.postState(SDataI.State.Load)

        lastFmStore.getInfoByName(name).enqueue(
            object : Callback<TagInfoXML> {
                override fun onResponse(call: Call<TagInfoXML>, response: Response<TagInfoXML>) {
                    response.body()?.let {
                        tagByName.setData(it.tag?.toTag())
                    }

                    tagByName.postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<TagInfoXML>, t: Throwable) {
                    tagByName.networkError(t.message)
                }
            }
        )

        return tagByName
    }

    override fun getTop(limit: Int, page: Int): SDataI<List<Tag>> {
        with(tagTop) {
            postState(SDataI.State.Load)

            lastFmStore.getTop(page, limit).enqueue(
            object : Callback<TagTopXML> {
                override fun onResponse(call: Call<TagTopXML>, response: Response<TagTopXML>) {
                    response.body()?.let {
                        setData(it.tags?.map { t -> t.toTag() })
                    }
                    postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<TagTopXML>, t: Throwable) {
                    networkError(t.message)
                }
            })

            return this
        }
    }

    override fun getSimilar(name: String): SDataI<List<Tag>> {
        with(tagSimilar) {
            postState(SDataI.State.Load)

            lastFmStore.getSimilar(name).enqueue(
                object : Callback<TagSimilarXML> {
                    override fun onResponse(call: Call<TagSimilarXML>, response: Response<TagSimilarXML>) {
                        response.body()?.let {
                            setData(it.tags?.map { t -> t.toTag() })
                        }
                        postState(SDataI.State.NetOk)
                    }

                    override fun onFailure(call: Call<TagSimilarXML>, t: Throwable) {
                        networkError(t.message)
                    }
                }
            )

            return this
        }
    }

    override fun getTopAlbums(tag: String, page: Int, limit: Int): SDataI<List<Album>> {
        with(tagTopAlbums) {
            postState(SDataI.State.Load)

            lastFmStore.getTopAlbums(tag, page, limit).enqueue(
                object : Callback<TagTopAlbumsXML> {
                    override fun onResponse(call: Call<TagTopAlbumsXML>, response: Response<TagTopAlbumsXML>) {
                        response.body()?.let {
                            setData(it.albums?.map { t -> t.toAlbum() })
                        }
                        postState(SDataI.State.NetOk)
                    }

                    override fun onFailure(call: Call<TagTopAlbumsXML>, t: Throwable) {
                        networkError(t.message)
                    }
                }
            )

            return this
        }
    }

    override fun getTopArtists(tag: String, page: Int, limit: Int): SDataI<List<Artist>> {
        with(tagTopArtists) {
            postState(SDataI.State.Load)

            lastFmStore.getTopArtists(tag, page, limit).enqueue(
                object : Callback<TagTopArtistsXML> {
                    override fun onResponse(call: Call<TagTopArtistsXML>, response: Response<TagTopArtistsXML>) {
                        response.body()?.let {
                            setData(it.artists?.map { t -> t.toArtist() })
                        }
                        postState(SDataI.State.NetOk)
                    }

                    override fun onFailure(call: Call<TagTopArtistsXML>, t: Throwable) {
                        networkError(t.message)
                    }
                }
            )

            return this
        }
    }

    override fun getTopTags(): SDataI<List<Tag>> {
        with(tagTopTags) {
            postState(SDataI.State.Load)

            lastFmStore.getTopTags().enqueue(
                object : Callback<TagTopTagsXML> {
                    override fun onResponse(call: Call<TagTopTagsXML>, response: Response<TagTopTagsXML>) {
                        response.body()?.let {
                            setData(it.tags?.map { t -> t.toTag() })
                        }
                        postState(SDataI.State.NetOk)
                    }

                    override fun onFailure(call: Call<TagTopTagsXML>, t: Throwable) {
                        networkError(t.message)
                    }
                }
            )

            return this
        }
    }

    override fun getTopTracks(name: String, page: Int, limit: Int): SDataI<List<Track>> {
        with(tagTopTracks) {
            postState(SDataI.State.Load)

            lastFmStore.getTopTracks(name, page, limit).enqueue(
                object : Callback<TagTopTracksXML> {
                    override fun onResponse(call: Call<TagTopTracksXML>, response: Response<TagTopTracksXML>) {
                        response.body()?.let {
                            setData(it.tracks?.map { t -> t.toTrack() })
                        }
                        postState(SDataI.State.NetOk)
                    }

                    override fun onFailure(call: Call<TagTopTracksXML>, t: Throwable) {
                        networkError(t.message)
                    }
                }
            )

            return this
        }
    }

    companion object {
        var repo: ITagRepo? = null
        fun getInstance(): ITagRepo {
            if (repo == null) {
                repo = TagRepo()
            }
            return repo as ITagRepo
        }
    }
}