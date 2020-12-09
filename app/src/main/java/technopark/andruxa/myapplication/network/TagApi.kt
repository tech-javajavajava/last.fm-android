package technopark.andruxa.myapplication.network

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TagApi {
    @Xml(name = "lfm")
    class TagChartResponse {
        @Element
        var tags: TagsList? = null
    }

    @Xml(name = "tags")
    class TagsList {
//        @Attribute
//        var page: Int? = null
//        @Attribute
//        var perPage: Int? = null
//        @Attribute
//        var totalPages: Int? = null
//        @Attribute
//        var total: Int? = null
        @Element
        var tags: List<Tag>? = null
    }

    @Xml(name = "tag")
    class Tag {
        @PropertyElement
        var name: String? = null
//        @PropertyElement
//        var url: String? = null
//        @PropertyElement
//        var reach: Int? = null
//        @PropertyElement
//        var taggings: Int? = null
//        @PropertyElement
//        var streamable: String? = null
//        @PropertyElement
//        var wiki: String? = null
    }

    @Xml(name = "lfm")
    class TagTopArtistsResponse {
        @Element
        var topartists: TopArtists? = null
    }

    @Xml(name = "topartists")
    class TopArtists {
//        @Attribute
//        var tag: String? = null
//        @Attribute
//        var page: Int? = null
//        @Attribute
//        var perPage: Int? = null
//        @Attribute
//        var totalPages: Int? = null
//        @Attribute
//        var total: Int? = null
        @Element
        var artists: List<TagArtist>? = null
    }

    @Xml(name = "artist")
    class TagArtist {
//        @Attribute
//        var rank: Int? = null
//        @PropertyElement
//        var name: String? = null
//        var mbid: String? = null
//        var url: String? = null
//        var streamable: String? = null
        @Element
        var images: List<Api.Image>? = null
    }

    @GET("2.0/?method=chart.getTopTags&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getChart(): Call<TagChartResponse>

    @GET("2.0/?method=chart.getTopTags&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getChart(@Query("limit") limit: Int): Call<TagChartResponse>

    @GET("2.0/?method=tag.getTopArtists&api_key=3b8a89498b5ab698a8966a966e97c5a1")
    fun getTopArtists(@Query("tag") tag: String, @Query("limit") limit: Int): Call<TagTopArtistsResponse>
}