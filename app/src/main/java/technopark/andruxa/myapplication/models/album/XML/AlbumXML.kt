package technopark.andruxa.myapplication.models.album.XML

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.models.album.Album
import technopark.andruxa.myapplication.models.album.AlbumSearchResponse
import technopark.andruxa.myapplication.models.album.AlbumSearchResults
import technopark.andruxa.myapplication.network.Api




@Xml(name = "lfm")
class AlbumSearchResponseXML: AlbumSearchResponse {
    @Element
    override var results: AlbumSearchResults? = null
}

@Xml(name = "results")
class AlbumSearchResultsXML: AlbumSearchResults {
    @PropertyElement
    override var totalResults: Int? = null

    @Element
    @Path("albummatches")
    override var albums: List<AlbumXML>? = null
    //        var query: String? = null
    //        var startIndex: Int? = null
    //        var itemsPerPage: Int? = null
}

@Xml
class AlbumXML: Album {
    @PropertyElement
    override val name: String? = null
    @PropertyElement
    override val artist: String? = null
    // var url: String? = null
    @Element
    var images: List<Api.Image>? = null
    // var streamable: String? = null
    // var mbid: String? = null
}