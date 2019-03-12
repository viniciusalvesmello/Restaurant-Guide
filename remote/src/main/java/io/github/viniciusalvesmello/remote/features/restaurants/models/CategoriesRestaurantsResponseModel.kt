package io.github.viniciusalvesmello.remote.features.restaurants.models

data class CategoriesRestaurantsResponseModel(
    val categories: List<Categories>?
) {
    companion object {
        data class Categories(
            val categories: Category?
        )

        data class Category(
            val id: Int?,
            val name: String?
        )
    }
}

