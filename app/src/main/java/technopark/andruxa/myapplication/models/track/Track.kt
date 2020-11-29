package technopark.andruxa.myapplication.models.track

import technopark.andruxa.myapplication.models.CanBeBroken
import technopark.andruxa.myapplication.models.additional.Wiki
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.artist.Artist
import technopark.andruxa.myapplication.models.tag.Tag

interface Track: CanBeBroken {
    val id: Int
    val name: String
    val mbid: String
    val url: String
    val duration: Int
    val isStreamable: Boolean
    val listenerNum: Int
    val playCount: Int
    val artist: Artist
    val album: Album
    val topTags: List<Tag>
    val wiki: Wiki
}
