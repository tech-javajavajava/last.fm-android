package technopark.andruxa.myapplication.models

import java.util.*

interface Tag {
    val name: String
    val url: String
    val reach: Int
    val taggings: Int
    val isStreamable: Boolean
    val wiki: Wiki
}
