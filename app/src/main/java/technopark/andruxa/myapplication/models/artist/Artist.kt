package technopark.andruxa.myapplication.models.artist

import technopark.andruxa.myapplication.models.additional.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Image
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.additional.Wiki

interface Artist: CanBeBroken {
    var name: String
    var mbid: String
    var url: String
    var image: Image
    var isStreamable: Boolean
    var listeners: Int
    var plays: Int
    var similar: List<Artist>
    var tags: List<Tag>
    var bio: Wiki
}