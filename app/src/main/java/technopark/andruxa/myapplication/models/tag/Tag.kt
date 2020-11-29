package technopark.andruxa.myapplication.models.tag

import technopark.andruxa.myapplication.models.additional.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Wiki

interface Tag: CanBeBroken {
    val name: String
    val url: String
    val reach: Int
    val taggings: Int
    val isStreamable: Boolean
    val wiki: Wiki
}
