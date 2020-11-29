package technopark.andruxa.myapplication.domain.track

import technopark.andruxa.myapplication.data.track.TrackRepo
import technopark.andruxa.myapplication.domain.TrackUseCase
import technopark.andruxa.myapplication.models.track.Track

class TrackUseCase(val trackRepo: TrackRepo) : TrackUseCase {
    override fun Love(track: String, artist: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun AddTags(track: String, artist: String, tags: Array<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun RemoveTags(track: String, artist: String, tags: Array<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun Search(track: String, artist: String?): Array<Track> {
        TODO("Not yet implemented")
    }

}