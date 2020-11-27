package technopark.andruxa.myapplication.network

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackApi {
    @Xml(name = "lfm")
    class TrackSearchResponse {
        @Element
        var results: TrackSearchResults? = null
    }

    @Xml(name = "results")
    class TrackSearchResults {
//        var query: String? = null
        @PropertyElement
        var totalResults: Int? = null
//        var startIndex: Int? = null
//        var itemsPerPage: Int? = null
        @Path("trackmatches")
        @Element
        var tracks: List<Track>? = null
    }

    @Xml
    class Track {
        @PropertyElement
        var name: String? = null
        @PropertyElement
        var artist: String? = null
//        @PropertyElement
//        var url: String? = null
//        @PropertyElement
//        var streamable: String? = null
//        @PropertyElement
//        var listeners: Int? = null
        @Element
        var images: List<Api.Image>? = null
//        @PropertyElement
//        var mbid: String? = null
    }

    @GET("2.0/?method=track.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("track") track: String): Call<TrackSearchResponse>

    @GET("2.0/?method=track.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("track") track: String, @Query("limit") limit: Int): Call<TrackSearchResponse>
}