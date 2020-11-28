package technopark.andruxa.myapplication.models

interface Album {
    val name: String
    val artist: String
    val id: Int
    val url: String
    val mbid: String
    val releaseDate: String
    val imageSmall: String
    val imageMedium: String
    val imageLarge: String
    val listenerNum: Int
    val playCount: Int
}
