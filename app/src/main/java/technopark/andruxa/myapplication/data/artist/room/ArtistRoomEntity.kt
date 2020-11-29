package technopark.andruxa.myapplication.data.artist.room

import androidx.room.Entity
import technopark.andruxa.myapplication.models.image.Image
import technopark.andruxa.myapplication.models.wiki.Wiki
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

@Entity
class ArtistRoomEntity: Artist {
    override var name: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var mbid: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var url: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var image: Image
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
    override var similar: List<Artist>
        get() = TODO("Not yet implemented")
        set(value) {}
    override var tags: List<Tag>
        get() = TODO("Not yet implemented")
        set(value) {}
    override var bio: Wiki
        get() = TODO("Not yet implemented")
        set(value) {}
    override var errorCode: Int
        get() = TODO("Not yet implemented")
        set(value) {}
    override var message: String
        get() = TODO("Not yet implemented")
        set(value) {}
}