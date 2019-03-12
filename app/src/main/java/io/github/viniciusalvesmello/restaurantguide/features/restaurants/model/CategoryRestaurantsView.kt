package io.github.viniciusalvesmello.restaurantguide.features.restaurants.model

data class CategoryRestaurantsView(
    val id: Int,
    val name: String
) {
    companion object {
        fun toEmpty() = CategoryRestaurantsView(
            id = 0,
            name = ""
        )
    }
}