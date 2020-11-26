package technopark.andruxa.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackApi {
    class SearchResponse {
        var results: SearchResults? = null
    }

    class SearchResults {
        var query: String? = null
        var totalResults: Int? = null
        var startIndex: Int? = null
        var itemsPerPage: Int? = null
        var trackmatches: TrackMatches? = null
    }

    class TrackMatches {
        var track: Array<Track>? = null
    }

    class Track {
        var name: String? = null
        var artist: String? = null
        var url: String? = null
        var streamable: String? = null
        var listeners: Int? = null
        var image: Array<Image>? = null
        var mbid: String? = null
    }

    class Image {
        var text: String? = null
        var size: String? = null
    }

    @GET("2.0/?method=track.search&api_key=3b8a89498b5ab698a8966a966e97c5a1&format=json")
    fun search(@Query("track") track: String): Call<SearchResponse>

    @GET("2.0/?method=track.search&api_key=3b8a89498b5ab698a8966a966e97c5a1&format=json")
    fun search(@Query("track") track: String, @Query("limit") limit: Int): Call<SearchResponse>
}