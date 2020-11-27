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
        var artists: List<Artist>? = null
    }

    @Xml
    class Artist {
        @PropertyElement
        var name: String? = null
//        var listeners: Int? = null
//        var mbid: String? = null
//        var url: String? = null
//        var streamable: String? = null
        @Element
        var images: List<Api.Image>? = null
    }

    @GET("2.0/?method=artist.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("artist") artist: String): Call<ArtistSearchResponse>

    @GET("2.0/?method=artist.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("artist") artist: String, @Query("limit") limit: Int): Call<ArtistSearchResponse>
}