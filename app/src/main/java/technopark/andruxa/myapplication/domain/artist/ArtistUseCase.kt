package technopark.andruxa.myapplication.domain.artist

import technopark.andruxa.myapplication.data.artist.ArtistRepo
import technopark.andruxa.myapplication.domain.ArtistUseCase
import technopark.andruxa.myapplication.models.artist.Artist
import java.util.*

class ArtistUseCase(val artistRepo: ArtistRepo) : ArtistUseCase {
    override fun Get(name: String): Artist {
        if (isUUID(name)) {
            return artistRepo.getByMbid(name, null, null, null)
        } else {
            return artistRepo.getByName(name, null, null, null)
        }
    }

    override fun Search(query: String): Array<Artist>? {
        TODO("Not yet implemented")
    }

    private fun isUUID(string: String?): Boolean {
        return try {
            UUID.fromString(string)
            true
        } catch (ex: Exception) {
            false
        }
    }
}