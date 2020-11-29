package technopark.andruxa.myapplication.models.tag

import technopark.andruxa.myapplication.models.additional.CanBeBroken

interface Tag: CanBeBroken {
    val name: String
    val url: String
    val reach: Int
    val taggings: Int
    val isStreamable: Boolean
    var wikiPublished: String
    var wikiSummary: String
    var wikiContent: String
}
