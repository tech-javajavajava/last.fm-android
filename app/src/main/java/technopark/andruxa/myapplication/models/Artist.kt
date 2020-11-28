package technopark.andruxa.myapplication.models

interface Artist {
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