package technopark.andruxa.myapplication.domain

import technopark.andruxa.myapplication.models.artist.Artist

interface ArtistUseCase {
    fun Get(name: String): Artist
}