package technopark.andruxa.myapplication.models.album

import technopark.andruxa.myapplication.models.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Image
import technopark.andruxa.myapplication.models.tag.Tag
import technopark.andruxa.myapplication.models.track.Track
import java.util.*

interface Album: CanBeBroken {
    val name: String
    val artistName: String
    val id: Int
    val mbid: String
    val url: String
    val release: Calendar
    val image: Image
    val listenerNum: Int
    val playCount: Int
    val topTags: List<Tag>
    val tracks: List<Track>
}
