package io.github.viniciusalvesmello.data.features.restaurants

import io.github.viniciusalvesmello.data.features.restaurants.mapper.toCategoryRestaurants
import io.github.viniciusalvesmello.data.features.restaurants.mapper.toRestaurant
import io.github.viniciusalvesmello.data.features.restaurants.mapper.toRestaurantReview
import io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface.RestaurantsCache
import io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface.RestaurantsRemote
import io.github.viniciusalvesmello.domain.features.restaurants.RestaurantsRepository
import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview
import io.reactivex.Single
import javax.inject.Inject

class DataRestaurantsRepository
@Inject constructor(
    private val restaurantsRemote: RestaurantsRemote,
    private val restaurantsCache: RestaurantsCache
) : RestaurantsRepository {
    override fun getCategoriesRestaurants(userKey: String): Single<List<CategoryRestaurants>> =
        restaurantsRemote.getCategoriesRestaurants(userKey = userKey).map {categoryEntityList ->
            categoryEntityList.map { categoryEntity ->
                categoryEntity.toCategoryRestaurants()
            }
        }

    override fun getRestaurants(
        userKey: String,
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ): Single<List<Restaurant>> = restaurantsRemote.getRestaurants(
        userKey = userKey,
        entityId = entityId,
        entityType = entityType,
        sort = sort,
        order = order,
        category = category,
        count = count,
        start = start
    ).map {restaurantEntityList ->
        restaurantEntityList.map {restaurantEntity ->
            restaurantEntity.toRestaurant()
        }
    }

    override fun getRestaurantReviews(
        userKey: String,
        restaurantId: Int,
        count: Int,
        start: Int
    ): Single<List<RestaurantReview>> = restaurantsRemote.getRestaurantReviews(
        userKey = userKey,
        restaurantId = restaurantId,
        count = count,
        start = start
    ).map {reviewEntityList ->
        reviewEntityList.map {reviewEntity ->
            reviewEntity.toRestaurantReview()
        }
    }
}