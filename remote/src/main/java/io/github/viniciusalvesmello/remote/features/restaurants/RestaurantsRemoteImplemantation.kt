package io.github.viniciusalvesmello.remote.features.restaurants

import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity
import io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface.RestaurantsRemote
import io.github.viniciusalvesmello.remote.ZomatoApiService
import io.github.viniciusalvesmello.remote.features.restaurants.mapper.toCategoryRestaurantsEntity
import io.github.viniciusalvesmello.remote.features.restaurants.mapper.toRestaurantEntity
import io.github.viniciusalvesmello.remote.features.restaurants.mapper.toRestaurantReviewEntity
import io.reactivex.Single
import javax.inject.Inject

class RestaurantsRemoteImplemantation
@Inject constructor(private val zomatoApiService: ZomatoApiService) : RestaurantsRemote {

    override fun getCategoriesRestaurants(userKey: String): Single<List<CategoryRestaurantsEntity>> =
        zomatoApiService.categoriesRestaurants(userKey).map { categoriesResponseModel ->
            categoriesResponseModel.categories?.map { categories ->
                categories.categories?.toCategoryRestaurantsEntity() ?: CategoryRestaurantsEntity.toEmpty()
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
    ): Single<List<RestaurantEntity>> = zomatoApiService.searchRestaurants(
        userKey = userKey,
        entityId = entityId,
        entityType = entityType,
        sort = sort,
        order = order,
        category = category,
        count = count,
        start = start
    ).map { restaurantsResponseModel ->
        restaurantsResponseModel.restaurants.map { restaurantResponseModel ->
            restaurantResponseModel?.restaurant?.toRestaurantEntity() ?: RestaurantEntity.toEmpty()
        }
    }

    override fun getRestaurantReviews(
        userKey: String,
        restaurantId: Int,
        count: Int,
        start: Int
    ): Single<List<RestaurantReviewEntity>> = zomatoApiService.restaurantReviews(
        userKey = userKey,
        restaurantId = restaurantId,
        count = count,
        start = start
    ).map { reviewsResponseModel ->
        reviewsResponseModel.user_reviews.map { review ->
            review?.review?.toRestaurantReviewEntity() ?: RestaurantReviewEntity.toEmpty()
        }
    }

}