package io.github.viniciusalvesmello.cache.features.restaurants.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_restaurants")
data class CategoryRestaurantsCacheModel(
    @PrimaryKey
    var categoryRestaurantsId: Int,
    var categoryRestaurantsName: String,
    var categoryRestaurantsDate: Int,
    var categoryRestaurantsTime: Float
)