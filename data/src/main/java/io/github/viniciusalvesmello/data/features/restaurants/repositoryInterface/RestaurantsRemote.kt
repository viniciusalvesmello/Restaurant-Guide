package io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface

import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity
import io.reactivex.Single

interface RestaurantsRemote {
    fun getCategoriesRestaurants(userKey: String) : Single<List<CategoryRestaurantsEntity>>

    fun getRestaurants(
        userKey: String,
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ) : Single<List<RestaurantEntity>>

    fun getRestaurantReviews(
        userKey: String,
        restaurantId : Int,
        count: Int,
        start: Int
    ) : Single<List<RestaurantReviewEntity>>
}