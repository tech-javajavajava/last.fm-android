package technopark.andruxa.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
    class SearchResponse {
        var results: SearchResults? = null
    }

    class SearchResults {
        var query: String? = null
        var totalResults: Int? = null
        var startIndex: Int? = null
        var itemsPerPage: Int? = null
        var albummatches: AlbumMatches? = null
    }

    class AlbumMatches {
        var album: Array<Album>? = null
    }

    class Album {
        var name: String? = null
        var artist: String? = null
        var url: String? = null
        var image: Array<Image>? = null
        var streamable: String? = null
        var mbid: String? = null
    }

    class Image {
        var text: String? = null
        var size: String? = null
    }

    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1&format=json")
    fun search(@Query("album") album: String): Call<SearchResponse>

    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1&format=json")
    fun search(@Query("album") album: String, @Query("limit") limit: Int): Call<SearchResponse>
}