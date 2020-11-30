package technopark.andruxa.myapplication.network

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import technopark.andruxa.myapplication.Constants

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

    @Xml(name = "lfm")
    class TrackResponse {
        @Element
        var track: Track? = null
    }

    @Xml(name = "track")
    class Track {
        @PropertyElement
        var name: String? = null
//        @PropertyElement
//        var mbid: String? = null
//        @PropertyElement
//        var url: String? = null
        @PropertyElement
        var duration: Int? = null
//        @PropertyElement
//        var streamable: String? = null
        @PropertyElement
        var listeners: Int? = null
        @PropertyElement
        var playcount: Int? = null
        @Element
        var artist: ChartTrackArtist? = null
        @Element
        var album: TrackAlbum? = null
        @Path("toptags")
        @Element
        var topTags: List<TrackTag>? = null
        @Element
        var wiki: Wiki? = null
    }

    @Xml(name = "album")
    class TrackAlbum {
        @PropertyElement
        var artist: String? = null
        @PropertyElement
        var title: String? = null
//        @PropertyElement
//        var mbid: String? = null
//        @PropertyElement
//        var url: String? = null
        @Element
        var images: List<Api.Image>? = null
    }

    @Xml(name = "tag")
    class TrackTag {
        @PropertyElement
        var name: String? = null
//        @PropertyElement
//        var url: String? = null
    }

    @Xml(name = "wiki")
    class Wiki {
//        @PropertyElement
//        var publushed: String? = null
        @PropertyElement
        var summary: String? = null
        @PropertyElement
        var content: String? = null
    }

    @Xml(name = "lfm")
    class TrackSimilarResponse {
        @Path("similartracks")
        @Element
        var similar: List<SimilarTrack>? = null
    }

    @Xml(name = "track")
    class SimilarTrack {
        @PropertyElement
        var name: String? = null
//        @PropertyElement
//        var playcount: Int? = null
//        @PropertyElement
//        var mbid: String? = null
//        @PropertyElement
//        var match: Float? = null
//        @PropertyElement
//        var url: String? = null
//        @PropertyElement
//        var streamable: String? = null
//        @PropertyElement
//        var duration: Int? = null
        @Element
        var artist: ChartTrackArtist? = null
        @Element
        var images: List<Api.Image>? = null
    }

    @GET("2.0/?method=track.search&api_key=${Constants.lastFmApiKey}")
    fun search(@Query("track") track: String): Call<TrackSearchResponse>

    @GET("2.0/?method=track.search&api_key=${Constants.lastFmApiKey}")
    fun search(
        @Query("track") track: String,
        @Query("limit") limit: Int
    ): Call<TrackSearchResponse>

    @GET("2.0/?method=chart.getTopTracks&api_key=${Constants.lastFmApiKey}")
    fun getChart(): Call<TrackChartResponse>

    @GET("2.0/?method=chart.getTopTracks&api_key=${Constants.lastFmApiKey}")
    fun getChart(@Query("limit") limit: Int): Call<TrackChartResponse>

    @GET("2.0/?method=track.getInfo&api_key=${Constants.lastFmApiKey}")
    fun getTrack(
        @Query("track") track: String,
        @Query("artist") artist: String
    ): Call<TrackResponse>

    @GET("2.0/?method=track.getSimilar&api_key=${Constants.lastFmApiKey}&limit=${Constants.similarTracksAmount}")
    fun getSimilarTracks(
        @Query("track") track: String,
        @Query("artist") artist: String
    ): Call<TrackSimilarResponse>
}