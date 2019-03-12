package io.github.viniciusalvesmello.remote.features.restaurants.models

data class RestaurantReviewsResponseModel(
    val reviews_count: Int,
    val reviews_start: Int,
    val reviews_shown: Int,
    val user_reviews: List<Review?>
) {
    companion object {
        data class Review(
            val review: ReviewDetail?
        )

        data class ReviewDetail(
            val rating: Float?,
            val review_text: String?,
            val rating_text: String?,
            val id: Int?,
            val review_time_friendly: String?,
            val user : User?
        )

        data class User (
            val name : String?,
            val profile_image: String?
        )
    }
}