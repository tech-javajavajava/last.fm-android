package technopark.andruxa.myapplication.data.tag.room

import technopark.andruxa.myapplication.data.additional.room.tag.TagRoomDao
import technopark.andruxa.myapplication.data.additional.room.tag.TagRoomEntity
import technopark.andruxa.myapplication.data.additional.room.tag.fromTag
import technopark.andruxa.myapplication.data.tag.TagRepo
import technopark.andruxa.myapplication.models.tag.Tag

class TagRoomRepo : TagRepo {
    override fun getByName(name: String): Tag {
        return TagRoomDao.get().getByName(name)
    }

    override fun save(vararg tags: Tag): List<Tag> {
        for (tag in tags) {
            if (tag.isBroken()) return emptyList()
        }

        val roomTagsList = tags.map { fromTag(it) }
        TagRoomDao.get().save(*roomTagsList.toTypedArray())
        return roomTagsList
    }

    override fun setTop(vararg tags: Tag): List<Tag> {
        for (tag in tags) {
            if (tag.isBroken()) return emptyList()
        }

        val roomTagsList = tags.map { fromTag(it).apply { isTop = true } }
        TagRoomDao.get().save(*roomTagsList.toTypedArray())
        return roomTagsList
    }

    override fun deleteByName(name: String): Tag {
        val tag = getByName(name) as TagRoomEntity
        if (tag.isBroken()) {
            return tag.apply { notFound() }
        }

        TagRoomDao.get().delete(tag)
        return tag
    }

    override fun getTop(limit: Int, page: Int): List<Tag> {
        return TagRoomDao.get().getTop(limit, limit * (page - 1))
    }
}