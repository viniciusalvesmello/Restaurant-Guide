package io.github.viniciusalvesmello.domain.features.restaurants.models

data class Restaurant(
    val id: Int,
    val name: String,
    val cuisines: String,
    val phoneNumbers : String,
    val thumb: String,
    val image: String,
    val rating: String,
    val ratingDescription: String,
    val locality: String,
    val address: String,
    val latitude: String,
    val longitude: String
)