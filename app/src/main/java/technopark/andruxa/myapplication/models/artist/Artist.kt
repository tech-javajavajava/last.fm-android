package technopark.andruxa.myapplication.models.artist

import technopark.andruxa.myapplication.models.additional.CanBeBroken
import technopark.andruxa.myapplication.models.tag.Tag

interface Artist: CanBeBroken {
    var name: String
    var mbid: String
    var url: String
    var imageSmallUrl: String
    var imageMediumUrl: String
    var imageLargeUrl: String
    var isStreamable: Boolean
    var listeners: Int
    var plays: Int
    var similar: List<Artist>?
    var tags: List<Tag>?
    var wikiPublished: String
    var wikiSummary: String
    var wikiContent: String
}