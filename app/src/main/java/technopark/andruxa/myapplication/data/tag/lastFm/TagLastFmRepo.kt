package technopark.andruxa.myapplication.data.tag.lastFm

import technopark.andruxa.myapplication.data.additional.lastFm.Requester
import technopark.andruxa.myapplication.data.additional.lastFm.tag.TagInfoXML
import technopark.andruxa.myapplication.data.tag.TagRepo
import technopark.andruxa.myapplication.models.tag.Tag

class TagLastFmRepo: TagRepo {
    override fun getByName(name: String): Tag {
        val response = Requester.getInstance().tagsApi.getInfoByName(name).execute()
        if (response.isSuccessful) {
            return response.body()!!
        }

        return TagInfoXML().apply { setLastFmError(response.errorBody().toString()) }
    }

    override fun save(vararg tags: Tag): List<Tag> {
        return emptyList()
    }

    override fun deleteByName(name: String): Tag {
        return TagInfoXML().apply { setNotPermitted() }
    }

    override fun getTop(limit: Int, page: Int): List<Tag> {
        val response = Requester.getInstance().tagsApi.getTop(page, limit).execute()
        if (response.isSuccessful) {
            return response.body()!!.tags
        }

        return emptyList()
    }

    override fun setTop(vararg tags: Tag): List<Tag> {
        return emptyList()
    }
}