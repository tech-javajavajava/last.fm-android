package technopark.andruxa.myapplication.models.album

import technopark.andruxa.myapplication.models.additional.CanBeBroken
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track

interface Album: CanBeBroken {
    var name: String
    var artistName: String
    var id: Int
    var mbid: String
    var url: String
    var release: String
    var imageSmallUrl: String
    var imageMediumUrl: String
    var imageLargeUrl: String
    var listenerNum: Int
    var playCount: Int
    var topTags: List<Tag>
    var tracks: List<Track>
}
