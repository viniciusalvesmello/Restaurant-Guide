package io.github.viniciusalvesmello.domain.features.restaurants

import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview
import io.reactivex.Single

interface RestaurantsRepository {
    fun getCategoriesRestaurants(userKey: String) : Single<List<CategoryRestaurants>>

    fun getRestaurants(
        userKey: String,
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ) : Single<List<Restaurant>>

    fun getRestaurantReviews(
        userKey: String,
        restaurantId : Int,
        count: Int,
        start: Int
    ) : Single<List<RestaurantReview>>
}