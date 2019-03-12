package io.github.viniciusalvesmello.restaurantguide.features.cities.model

data class CityView(
    val id: Int,
    val name: String,
    val imageUrl : String = ""
) {
    companion object {
        fun toEmpty() = CityView(
            id = 0,
            name = "",
            imageUrl = ""
        )
    }
}