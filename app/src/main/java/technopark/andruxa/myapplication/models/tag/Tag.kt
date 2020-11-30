package technopark.andruxa.myapplication.models.tag

import technopark.andruxa.myapplication.models.additional.CanBeBroken

interface Tag: CanBeBroken {
    var name: String
    var url: String
    var reach: Int
    var taggings: Int
    var isStreamable: Boolean
    var wikiPublished: String
    var wikiSummary: String
    var wikiContent: String
}
