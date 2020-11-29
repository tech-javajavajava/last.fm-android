package technopark.andruxa.myapplication.data.tag

import technopark.andruxa.myapplication.models.tag.Tag

interface TagRepo {
    fun getByName(name: String, lang: String): Tag
}