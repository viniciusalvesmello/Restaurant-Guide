package io.github.viniciusalvesmello.restaurantguide.features.restaurants.model

data class RestaurantView(
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
) {
    companion object {
        fun toEmpty() = RestaurantView(
            id = 0,
            name = "",
            cuisines = "",
            phoneNumbers = "",
            thumb = "",
            image = "",
            rating = "",
            ratingDescription = "",
            locality = "",
            address = "",
            latitude = "",
            longitude = ""
        )
    }
}