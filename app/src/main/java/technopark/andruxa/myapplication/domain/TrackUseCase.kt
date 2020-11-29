package technopark.andruxa.myapplication.domain

import technopark.andruxa.myapplication.models.track.Track

interface TrackUseCase {
    fun Love(track: String, artist: String): Boolean
    fun AddTags(track: String, artist: String, tags: Array<String>): Boolean
    fun RemoveTags(track: String, artist: String, tags: Array<String>): Boolean
    fun Search(track: String, artist: String?): Array<Track>
}