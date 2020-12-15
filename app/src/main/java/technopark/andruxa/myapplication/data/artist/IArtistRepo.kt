package technopark.andruxa.myapplication.data.artist

import technopark.andruxa.myapplication.data.SDataI
import technopark.andruxa.myapplication.models.artist.Artist

interface IArtistRepo {

    val artistById: SDataI<Artist>

    val artistByName: SDataI<Artist>

    val artistSearch: SDataI<List<Artist>>

    val artistTop: SDataI<List<Artist>>

    fun getById(
        id: String,
        userName: String?,
    ): SDataI<Artist>

    fun getByName(
        name: String,
        userName: String?,
    ): SDataI<Artist>

    fun searchByName(
        name: String,
        limit: Int = 50,
        page: Int = 1,
    ): SDataI<List<Artist>>

    fun getTop(limit: Int = 50, page: Int = 1): SDataI<List<Artist>>
}