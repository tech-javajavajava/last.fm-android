package technopark.andruxa.myapplication.data.user

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.track.Track
import technopark.andruxa.myapplication.models.user.User

interface UserRepoI {
    val currentUser: SDataI<User>

    val loveTracks: SDataI<List<Track>>

    val recenTracks: SDataI<List<Track>>

    fun getCurrent(): SDataI<User>

    fun getLovedTracks(name: String, limit: Int, page: Int): SDataI<List<Track>>

    fun getRecentTracks(name: String, limit: Int, page: Int): SDataI<List<Track>>
}
