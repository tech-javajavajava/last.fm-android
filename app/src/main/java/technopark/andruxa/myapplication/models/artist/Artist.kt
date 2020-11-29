package technopark.andruxa.myapplication.models.artist

import technopark.andruxa.myapplication.models.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Image
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.additional.Wiki

interface Artist: CanBeBroken {
    val name: String
    val mbid: String
    val url: String
    val image: Image
    val isStreamable: Boolean
    val listeners: Int
    val plays: Int
    val similar: List<Artist>
    val tags: List<Tag>
    val bio: Wiki
}