package technopark.andruxa.myapplication.data.additional.room.track

import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

class TrackRoomEntity: Track {
    override var id: Int = -1
    override var name: String = ""
    override var mbid: String = ""
    override var url: String = ""
    override var duration: Int = -1
    override var isStreamable: Boolean = false
    override var listenerNum: Int = -1
    override var playCount: Int = -1
    override var artist: Artist? = null
    override var album: Album? = null
    override var topTags: List<Tag>? = null
    override var wikiPublished: String = ""
    override var wikiSummary: String = ""
    override var wikiContent: String = ""
    override var errorCode: Int = -1
    override var message: String = ""
}