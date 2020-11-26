package technopark.andruxa.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApi {
    class SearchResponse {
        var results: SearchResults? = null
    }

    class SearchResults {
        var query: String? = null
        var totalResults: Int? = null
        var startIndex: Int? = null
        var itemsPerPage: Int? = null
        var artistmatches: ArtistMatches? = null
    }

    class ArtistMatches {
        var artists: Array<Artist>? = null
    }

    class Artist {
        var name: String? = null
        var listeners: Int? = null
        var mbid: String? = null
        var url: String? = null
        var streamable: String? = null
        var image: Array<Image>? = null
    }

    class Image {
        var text: String? = null
        var size: String? = null
    }

    @GET("2.0/?method=artist.search&api_key=3b8a89498b5ab698a8966a966e97c5a1&format=json")
    fun search(@Query("artist") artist: String): Call<SearchResponse>

    @GET("2.0/?method=artist.search&api_key=3b8a89498b5ab698a8966a966e97c5a1&format=json")
    fun search(@Query("artist") artist: String, @Query("limit") limit: Int): Call<SearchResponse>
}