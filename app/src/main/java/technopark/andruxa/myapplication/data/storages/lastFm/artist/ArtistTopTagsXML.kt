package technopark.andruxa.myapplication.data.storages.lastFm.artist

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml
import technopark.andruxa.myapplication.data.storages.lastFm.tag.TagXML

@Xml(name = "lfm")
class ArtistTopTagsXML {
    @Path("toptags")
    var tags: List<TagXML>? = null
}