package io.github.viniciusalvesmello.domain.features.restaurants.models

data class RestaurantReview(
    val id: Int,
    val reviewText : String,
    val rating: Float,
    val ratingDescription: String,
    val userName: String,
    val userProfileImage: String,
    val dateDescription: String
)