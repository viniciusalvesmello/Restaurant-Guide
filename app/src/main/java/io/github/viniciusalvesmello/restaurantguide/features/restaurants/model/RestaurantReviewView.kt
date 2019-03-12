package io.github.viniciusalvesmello.restaurantguide.features.restaurants.model

data class RestaurantReviewView(
    val id: Int,
    val reviewText : String,
    val rating: Float,
    val ratingDescription: String,
    val userName: String,
    val userProfileImage: String,
    val dateDescription: String
) {
    companion object {
        fun toEmpty() = RestaurantReviewView(
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