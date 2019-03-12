package io.github.viniciusalvesmello.cache.features.restaurants

import io.github.viniciusalvesmello.cache.features.restaurants.dao.CategoryRestaurantsCacheDao
import io.github.viniciusalvesmello.cache.features.restaurants.dao.RestaurantCacheDao
import io.github.viniciusalvesmello.cache.features.restaurants.dao.RestaurantReviewCacheDao
import io.github.viniciusalvesmello.cache.features.restaurants.mapper.*
import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity
import io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface.RestaurantsCache
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RestaurantsCacheImplemantation
@Inject constructor(
    private val categoryRestaurantsCacheDao: CategoryRestaurantsCacheDao,
    private val restaurantCacheDao: RestaurantCacheDao,
    private val restaurantReviewCacheDao: RestaurantReviewCacheDao
) : RestaurantsCache {

    companion object {
        private const val FORMAT_DATE = "yyyyMMdd"
        private const val FORMAT_HOUR = "HH"
        private const val FORMAT_MINUTE = "mm"
        private const val ONE_HOUR_IN_MINUTES = 60
        private const val ONE_HOUR_EXPIRATION_TIME = 1.0f
    }

    private var date: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0
    private var time: Float = 0.0f

    private fun updateTime() {
        date = (SimpleDateFormat(FORMAT_DATE, Locale.getDefault()).format(Date()) ?: "19000101").toInt()
        hour = (SimpleDateFormat(FORMAT_HOUR, Locale.getDefault()).format(Date()) ?: "00").toInt()
        minute = (SimpleDateFormat(FORMAT_MINUTE, Locale.getDefault()).format(Date()) ?: "00").toInt()
        time = hour.toFloat() + (minute.toFloat() / ONE_HOUR_IN_MINUTES)
    }

    override fun deleteExpiredCategoriesRestaurants() {
        updateTime()
        categoryRestaurantsCacheDao.deleteExpiredRestaurantReviewCache(
            categoryRestaurantsDate = date,
            categoryRestaurantsTime = time,
            expirationTime = ONE_HOUR_EXPIRATION_TIME
        )
    }

    override fun countCategoriesRestaurants(): Int = categoryRestaurantsCacheDao.countRestaurantReviewCache()

    override fun getCategoriesRestaurants(): Single<List<CategoryRestaurantsEntity>> =
        categoryRestaurantsCacheDao.selectAllRestaurantReviewCache().map {listCategoryRestaurantsCacheModel ->
            listCategoryRestaurantsCacheModel.map { categoryRestaurantsCacheModel ->
                categoryRestaurantsCacheModel.toCategoryRestaurantsEntity()
            }
        }

    override fun storeDataCategoriesRestaurants(listCategoryRestaurantsEntity: List<CategoryRestaurantsEntity>) {
        updateTime()
        categoryRestaurantsCacheDao.insertAllRestaurantReviewCache(
            listCategoryRestaurantsEntity.map {categoryRestaurantsEntity ->
                categoryRestaurantsEntity.toCategoryRestaurantsCacheModel(
                    categoryRestaurantsDate = date,
                    categoryRestaurantsTime = time
                )
            }
        )
    }

    override fun deleteExpiredRestaurants() {
        updateTime()
        restaurantCacheDao.deleteExpiredRestaurantsCache(
            restaurantDate = date,
            restaurantTime = time,
            expirationTime = ONE_HOUR_EXPIRATION_TIME
        )
    }

    override fun countRestaurants(
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ): Int = restaurantCacheDao.countAllRestaurantsCache(
        restaurantEntityId = entityId,
        restaurantEntityType = entityType,
        restaurantSort = sort,
        restaurantOrder = order,
        restaurantCategory = category,
        restaurantCount = count,
        restaurantStart = start
    )

    override fun getRestaurants(
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ): Single<List<RestaurantEntity>> = restaurantCacheDao.selectAllRestaurantsCache(
        restaurantEntityId = entityId,
        restaurantEntityType = entityType,
        restaurantSort = sort,
        restaurantOrder = order,
        restaurantCategory = category,
        restaurantCount = count,
        restaurantStart = start
    ).map {listRestaurantsCacheModel ->
        listRestaurantsCacheModel.map {restaurantsCacheModel ->
            restaurantsCacheModel.toRestaurantEntity()
        }
    }

    override fun storeDataRestaurants(
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int,
        listRestaurantEntity: List<RestaurantEntity>
    ) {
        updateTime()
        restaurantCacheDao.insertAllRestaurantsCache(
            listRestaurantEntity.map {restaurantEntity ->
                restaurantEntity.toRestaurantCacheModel(
                    restaurantEntityId = entityId,
                    restaurantEntityType = entityType,
                    restaurantSort = sort,
                    restaurantOrder = order,
                    restaurantCategory = category,
                    restaurantCount = count,
                    restaurantStart = start,
                    restaurantDate = date,
                    restaurantTime = time
                )
            }
        )
    }

    override fun deleteRestaurantReviews() {
        updateTime()
        restaurantReviewCacheDao.deleteExpiredRestaurantReviewCache(
            restaurantReviewDate = date,
            restaurantReviewTime = time,
            expirationTime = ONE_HOUR_EXPIRATION_TIME
        )
    }

    override fun countRestaurantReviews(restaurantId: Int, count: Int, start: Int): Int =
        restaurantReviewCacheDao.countRestaurantReviewCache(
            restaurantReviewRestaurantId = restaurantId,
            restaurantReviewCount = count,
            restaurantReviewStart = start
        )

    override fun getRestaurantReviews(restaurantId: Int, count: Int, start: Int): Single<List<RestaurantReviewEntity>> =
        restaurantReviewCacheDao.selectAllRestaurantReviewCache(
            restaurantReviewRestaurantId = restaurantId,
            restaurantReviewCount = count,
            restaurantReviewStart = start
        ).map {listRestaurantReviewCacheModel ->
            listRestaurantReviewCacheModel.map {restaurantReviewCacheModel ->
                restaurantReviewCacheModel.toRestaurantReviewEntity()
            }
        }

    override fun storeDataRestaurantReviews(
        restaurantId: Int,
        count: Int,
        start: Int,
        listRestaurantReviewEntity: List<RestaurantReviewEntity>
    ) {
        updateTime()
        restaurantReviewCacheDao.insertAllRestaurantReviewCache(
            listRestaurantReviewEntity.map { restaurantReviewEntity ->
                restaurantReviewEntity.toRestaurantReviewCacheModel(
                    restaurantReviewRestaurantId = restaurantId,
                    restaurantReviewCount = count,
                    restaurantReviewStart = start,
                    restaurantReviewDate = date,
                    restaurantReviewTime = time
                )
            }
        )
    }

}