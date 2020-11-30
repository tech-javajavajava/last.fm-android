package technopark.andruxa.myapplication.models

import technopark.andruxa.myapplication.network.TrackApi

class Tag(val name: String?) {
    constructor(tag: TrackApi.TrackTag) : this(tag.name)
}