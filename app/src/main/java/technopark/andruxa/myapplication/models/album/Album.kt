package technopark.andruxa.myapplication.models.album

import technopark.andruxa.myapplication.models.additional.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Image
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track
import java.util.*

interface Album: CanBeBroken {
    var name: String
    var artistName: String
    var id: Int
    var mbid: String
    var url: String
    var release: String
    var image: Image
    var listenerNum: Int
    var playCount: Int
    var topTags: List<Tag>
    var tracks: List<Track>
}
