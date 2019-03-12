package io.github.viniciusalvesmello.remote.features.restaurants.models

data class RestaurantsResponseModel(
    val results_found: Int?,
    val results_start: Int?,
    val results_shown: Int?,
    val restaurants: List<Restaurant?>
) {
    companion object {
        data class Restaurant(
            val restaurant: RestaurantDetail?
        )

        data class RestaurantDetail(
            val id: Int?,
            val name: String?,
            val cuisines: String?,
            val phone_numbers: String?,
            val thumb: String?,
            val featured_image: String?,
            val user_rating: UserRating?,
            val location: Location?
        )

        data class UserRating(
            val aggregate_rating: String?,
            val rating_text: String?
        )

        data class Location(
            val address: String?,
            val latitude: String?,
            val longitude: String?,
            val locality_verbose: String?
        )
    }
}