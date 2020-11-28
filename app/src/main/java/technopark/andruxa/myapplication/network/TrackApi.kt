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
//        @PropertyElement
//        var totalResults: Int? = null
//        var startIndex: Int? = null
//        var itemsPerPage: Int? = null
        @Path("trackmatches")
        @Element
        var tracks: List<SearchTrack>? = null
    }

    @Xml(name = "track")
    class SearchTrack {
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

    @Xml(name = "lfm")
    class TrackChartResponse {
        @Element
        var tracks: TracksList? = null
    }

    @Xml(name = "tracks")
    class TracksList {
//        @Attribute
//        var page: Int? = null
//        @Attribute
//        var perPage: Int? = null
//        @Attribute
//        var totalPages: Int? = null
//        @Attribute
//        var total: Int? = null
        @Element
        var tracks: List<ChartTrack>? = null
    }

    @Xml(name = "track")
    class ChartTrack {
        @PropertyElement
        var name: String? = null
//        @PropertyElement
//        var duration: Int? = null
//        @PropertyElement
//        var playcount: Int? = null
//        @PropertyElement
//        var listeners: Int? = null
//        @PropertyElement
//        var mbid: String? = null
//        @PropertyElement
//        var url: String? = null
//        @PropertyElement
//        var streamable: String? = null
        @Element
        var artist: ChartTrackArtist? = null
        @Element
        var images: List<Api.Image>? = null
    }

    @Xml(name = "artist")
    class ChartTrackArtist {
        @PropertyElement
        var name: String? = null
//        @PropertyElement
//        var mbid: String? = null
//        @PropertyElement
//        var url: String? = null
    }

    class Track(var name: String?, var artist: String?, var images: List<Api.Image>?) {
        constructor(track: SearchTrack): this(track.name, track.artist, track.images)
        constructor(track: ChartTrack): this(track.name, track.artist!!.name, track.images)
    }

    @GET("2.0/?method=track.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("track") track: String): Call<TrackSearchResponse>

    @GET("2.0/?method=track.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("track") track: String, @Query("limit") limit: Int): Call<TrackSearchResponse>

    @GET("2.0/?method=chart.getTopTracks&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getChart(): Call<TrackChartResponse>

    @GET("2.0/?method=chart.getTopTracks&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getChart(@Query("limit") limit: Int): Call<TrackChartResponse>
}