package technopark.andruxa.myapplication.data.tag

import technopark.andruxa.myapplication.models.tag.Tag

interface TagRepo {
    fun getByName(name: String): Tag
    fun save(vararg tags: Tag): List<Tag>
    fun deleteByName(name: String): Tag
    fun getTop(): List<Tag>
    fun setTop(vararg tags: Tag): List<Tag>
}