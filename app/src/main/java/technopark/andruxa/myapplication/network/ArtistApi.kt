package technopark.andruxa.myapplication.network

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistApi {
    @Xml(name = "lfm")
    class ArtistSearchResponse {
        @Element
        var results: ArtistSearchResults? = null
    }

    @Xml(name = "results")
    class ArtistSearchResults {
//        var query: String? = null
        @PropertyElement
        var totalResults: Int? = null
//        var startIndex: Int? = null
//        var itemsPerPage: Int? = null
        @Path("artistmatches")
        @Element
        var artists: List<SearchArtist>? = null
    }

    @Xml(name = "artist")
    class SearchArtist {
        @PropertyElement
        var name: String? = null
//        var listeners: Int? = null
//        var mbid: String? = null
//        var url: String? = null
//        var streamable: String? = null
        @Element
        var images: List<Api.Image>? = null
    }

    @Xml(name = "lfm")
    class ArtistChartResponse {
        @Element
        var artists: ArtistsList? = null
    }

    @Xml(name = "artists")
    class ArtistsList {
//        @Attribute
//        var page: Int? = null
//        @Attribute
//        var perPage: Int? = null
//        @Attribute
//        var totalPages: Int? = null
//        @Attribute
//        var total: Int? = null
        @Element
        var artists: List<ChartArtist>? = null
    }

    @Xml(name = "artist")
    class ChartArtist {
        @PropertyElement
        var name: String? = null
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
        var images: List<Api.Image>? = null
    }

    class Artist(var name: String?, var images: List<Api.Image>?) {
        constructor(artist: SearchArtist): this(artist.name, artist.images)
        constructor(artist: ChartArtist): this(artist.name, artist.images)
    }

    @GET("2.0/?method=artist.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("artist") artist: String): Call<ArtistSearchResponse>

    @GET("2.0/?method=artist.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("artist") artist: String, @Query("limit") limit: Int): Call<ArtistSearchResponse>

    @GET("2.0/?method=chart.getTopArtists&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getChart(): Call<ArtistChartResponse>

    @GET("2.0/?method=chart.getTopArtists&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getChart(@Query("limit") limit: Int): Call<ArtistChartResponse>
}