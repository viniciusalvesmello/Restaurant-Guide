package io.github.viniciusalvesmello.cache.features.restaurants.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantCacheModel(
    @PrimaryKey(autoGenerate = false)
    var restaurantEntityId: Int,
    var restaurantEntityType: String,
    var restaurantSort: String,
    var restaurantOrder: String,
    var restaurantCategory: Int,
    var restaurantCount: Int,
    var restaurantStart: Int ,
    var restaurantId: Int,
    var restaurantName: String,
    var restaurantCuisines: String,
    var restaurantPhoneNumbers: String,
    var restaurantThumb: String,
    var restaurantImage: String,
    var restaurantRating: String,
    var restaurantRatingDescription: String,
    var restaurantLocality: String,
    var restaurantAddress: String,
    var restaurantLatitude: String,
    var restaurantLongitude: String,
    var restaurantDate: Int,
    var restaurantTime: Float
)