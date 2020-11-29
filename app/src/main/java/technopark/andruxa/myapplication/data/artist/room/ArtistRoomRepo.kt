package technopark.andruxa.myapplication.data.artist.room

import androidx.room.Entity
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

@Entity
class ArtistRoomRepo: Artist {
    override var name: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var mbid: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var url: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var imageSmallUrl: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var imageMediumUrl: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var imageLargeUrl: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var isStreamable: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    override var listeners: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var plays: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var similar: List<Artist>?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var tags: List<Tag>?
        get() = TODO("Not yet implemented")
        set(value) {}
    override var wikiPublished: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var wikiSummary: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var wikiContent: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var errorCode: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var message: String
        get() = TODO("Not yet implemented")
        set(value) {}
}