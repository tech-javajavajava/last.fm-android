package technopark.andruxa.myapplication.network

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
    @Xml(name = "lfm")
    class AlbumSearchResponse {
        @Element
        var results: AlbumSearchResults? = null
    }

    @Xml(name = "results")
    class AlbumSearchResults {
//        var query: String? = null
        @PropertyElement
        var totalResults: Int? = null
//        var startIndex: Int? = null
//        var itemsPerPage: Int? = null
        @Path("albummatches")
        @Element
        var albums: List<Album>? = null
    }

    @Xml
    class Album {
        @PropertyElement
        var name: String? = null
        @PropertyElement
        var artist: String? = null
//        var url: String? = null
        @Element
        var images: List<Api.Image>? = null
//        var streamable: String? = null
//        var mbid: String? = null
    }

    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("album") album: String): Call<AlbumSearchResponse>

    @GET("2.0/?method=album.search&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun search(@Query("album") album: String, @Query("limit") limit: Int): Call<AlbumSearchResponse>
}