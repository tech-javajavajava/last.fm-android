package technopark.andruxa.myapplication.models

interface Track {
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
