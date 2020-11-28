package technopark.andruxa.myapplication.models.album

interface Album {
    val name: String?
    val artist: String?
//    var url: String? = null
//    var images: List<Api.Image>? = null
//    var streamable: String? = null
//    var mbid: String? = null
}

interface AlbumSearchResults {
    val totalResults: Int?
    val albums: List<Album>?
    //        var startIndex: Int? = null
    //        var itemsPerPage: Int? = null
    //        var query: String? = null

}

interface AlbumSearchResponse {
    val results: AlbumSearchResults?
}

