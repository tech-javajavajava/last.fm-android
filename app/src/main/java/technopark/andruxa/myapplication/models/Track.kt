package technopark.andruxa.myapplication.models

import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.network.TrackApi

class Track(val name: String?, val artist: String?, val images: List<Api.Image>?) {
    constructor(track: TrackApi.SearchTrack): this(track.name, track.artist, track.images)
    constructor(track: TrackApi.ChartTrack): this(track.name, track.artist!!.name, track.images)
    constructor(track: TrackApi.SimilarTrack): this(track.name, track.artist!!.name, track.images)
    constructor(track: TrackApi.Track) : this(track.name, track.artist!!.name, track.album?.images) {
        duration = track.duration
        listeners = track.listeners
        playcount = track.playcount
        album = Album(track.album)
        topTags = track.topTags?.map { t -> Tag(t) }
        wiki = Wiki(track.wiki)
    }
    var duration: Int? = null
    var listeners: Int? = null
    var playcount: Int? = null
    var album: Album? = null
    var topTags: List<Tag>? = null
    var wiki: Wiki? = null

    class Wiki(val summary: String?, val content: String?) {
        constructor(wiki: TrackApi.Wiki?) : this(wiki?.summary, wiki?.content)
    }
}