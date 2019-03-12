package io.github.viniciusalvesmello.data.features.restaurants.model


data class RestaurantReviewEntity(
    val id: Int,
    val reviewText : String,
    val rating: Float,
    val ratingDescription: String,
    val userName: String,
    val userProfileImage: String,
    val dateDescription: String
) {
    companion object {
        fun toEmpty() = RestaurantReviewEntity(
            id = 0,
            reviewText = "",
            rating = 0.0f,
            ratingDescription = "",
            userName = "",
            userProfileImage = "",
            dateDescription = ""
        )
    }
}