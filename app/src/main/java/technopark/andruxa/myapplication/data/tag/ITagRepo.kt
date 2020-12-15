package technopark.andruxa.myapplication.data.tag

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.tag.Tag

interface ITagRepo {
    val tagByName: SDataI<Tag>
    val tagTop: SDataI<List<Tag>>

    fun getByName(name: String): SDataI<Tag>
    fun getTop(limit: Int = 50, page: Int = 1): SDataI<List<Tag>>
}