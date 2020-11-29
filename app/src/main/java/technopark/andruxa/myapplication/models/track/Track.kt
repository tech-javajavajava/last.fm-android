package technopark.andruxa.myapplication.models.track

import technopark.andruxa.myapplication.models.additional.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Wiki
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

interface Track: CanBeBroken {
    var id: Int
    var name: String
    var mbid: String
    var url: String
    var duration: Int
    var isStreamable: Boolean
    var listenerNum: Int
    var playCount: Int
    var artist: Artist
    var album: Album
    var topTags: List<Tag>
    var wiki: Wiki
}
