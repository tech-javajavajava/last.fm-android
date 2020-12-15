package technopark.andruxa.myapplication.data.artist

import androidx.lifecycle.LiveData
import technopark.andruxa.myapplication.models.artist.Artist

interface IArtistRepo {

    val artist: LiveData<Artist>

    val artists: LiveData<List<Artist>>

    fun getById(
        id: String,
        userName: String?,
    ): LiveData<Artist>

    fun getByName(
        name: String,
        userName: String?,
    ): LiveData<Artist>

    fun searchByName(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): LiveData<List<Artist>>

    fun getTop(limit: Int = 50, page: Int = 1): LiveData<List<Artist>>
}