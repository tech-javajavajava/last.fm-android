package technopark.andruxa.myapplication.data.tag.room

import technopark.andruxa.myapplication.data.tag.TagRepo
import technopark.andruxa.myapplication.models.tag.Tag

class TagRoomRepo: TagRepo {
    override fun getByName(name: String, lang: String): Tag {
        TODO("Not yet implemented")
    }
}