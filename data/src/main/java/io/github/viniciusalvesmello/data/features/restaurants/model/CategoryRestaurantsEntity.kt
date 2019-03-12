package io.github.viniciusalvesmello.data.features.restaurants.model

data class CategoryRestaurantsEntity(
    val id : Int,
    val name : String
) {
    companion object {
        fun toEmpty() = CategoryRestaurantsEntity(
            id = 0,
            name = ""
        )
    }
}