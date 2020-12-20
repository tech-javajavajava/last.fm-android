package technopark.andruxa.myapplication.data.tag

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import technopark.andruxa.myapplication.data.SData
import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.data.storages.lastFm.LastFmStore
import technopark.andruxa.myapplication.data.storages.lastFm.tag.TagInfoXML
import technopark.andruxa.myapplication.data.storages.lastFm.tag.TagTopXML
import technopark.andruxa.myapplication.models.tag.Tag

class TagRepo: ITagRepo {
    override var tagByName: SData<Tag> = SData()
        private set
    override var tagTop: SData<List<Tag>> = SData()
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
        tagTop.postState(SDataI.State.Load)

        lastFmStore.getTop(page, limit).enqueue(
            object : Callback<TagTopXML> {
                override fun onResponse(call: Call<TagTopXML>, response: Response<TagTopXML>) {
                    response.body()?.let {
                        tagTop.setData(it.tags?.map { t -> t.toTag() })
                    }
                    tagTop.postState(SDataI.State.NetOk)
                }

                override fun onFailure(call: Call<TagTopXML>, t: Throwable) {
                    tagTop.networkError(t.message)
                }
            }
        )

        return tagTop
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