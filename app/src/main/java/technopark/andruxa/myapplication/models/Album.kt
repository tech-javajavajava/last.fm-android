package technopark.andruxa.myapplication.models

import java.util.*

interface Album {
    val name: String
    val artist: String
    val id: Int
    val mbid: String
    val url: String
    val release: Calendar
    val imageSmall: String
    val imageMedium: String
    val imageLarge: String
    val listenerNum: Int
    val playCount: Int
    val topTags: List<Tag>
    val tracks: List<Track>
}
