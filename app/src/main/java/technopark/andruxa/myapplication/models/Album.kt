package technopark.andruxa.myapplication.models

import technopark.andruxa.myapplication.network.Api
import technopark.andruxa.myapplication.network.TrackApi

class Album(val name: String?, val artist: String?, val images: List<Api.Image>?) {
    constructor(album: TrackApi.TrackAlbum?) : this(album?.title, album?.artist, album?.images)
}