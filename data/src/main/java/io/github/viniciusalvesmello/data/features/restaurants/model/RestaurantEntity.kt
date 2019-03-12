package io.github.viniciusalvesmello.data.features.restaurants.model

data class RestaurantEntity(
    val id : Int,
    val name : String,
    val cuisines: String,
    val phoneNumbers : String,
    val thumb: String,
    val image: String,
    val rating: String,
    val ratingDescription: String,
    val locality: String,
    val address : String,
    val latitude : String,
    val longitude : String
) {
    companion object {
        fun toEmpty() = RestaurantEntity(
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