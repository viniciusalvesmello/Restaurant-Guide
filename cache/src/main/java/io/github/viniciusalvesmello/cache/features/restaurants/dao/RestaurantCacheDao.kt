package io.github.viniciusalvesmello.cache.features.restaurants.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.viniciusalvesmello.cache.features.restaurants.model.RestaurantCacheModel
import io.reactivex.Single


@Dao
interface RestaurantCacheDao {
    @Query(
        value = "delete " +
                "from restaurant " +
                "where (" +
                " (case when ((:restaurantDate - restaurantDate) >  1) " +
                "   then ((:restaurantDate - restaurantDate) * 24) " +
                "   else 0 " +
                " end) + " +
                " (case when ((:restaurantDate - restaurantDate) >= 1) " +
                "   then ((24 - restaurantTime) + :restaurantTime) " +
                "   else (:restaurantTime - restaurantTime) " +
                " end)" +
                ") > :expirationTime"
    )
    fun deleteExpiredRestaurantsCache(restaurantDate: Int, restaurantTime: Float, expirationTime: Float)

    @Query(
        value = "select count(*) " +
                "from restaurant " +
                "where restaurantEntityId   = :restaurantEntityId " +
                "and   restaurantEntityType = :restaurantEntityType " +
                "and   restaurantSort       = :restaurantSort " +
                "and   restaurantOrder      = :restaurantOrder " +
                "and   restaurantCategory   = :restaurantCategory " +
                "and   restaurantCount      = :restaurantCount " +
                "and   restaurantStart      = :restaurantStart"
    )
    fun countAllRestaurantsCache(
        restaurantEntityId: Int,
        restaurantEntityType: String,
        restaurantSort: String,
        restaurantOrder: String,
        restaurantCategory: Int,
        restaurantCount: Int,
        restaurantStart: Int
    ): Int

    @Query(
        "select * " +
                "from restaurant " +
                "where restaurantEntityId   = :restaurantEntityId " +
                "and   restaurantEntityType = :restaurantEntityType " +
                "and   restaurantSort       = :restaurantSort " +
                "and   restaurantOrder      = :restaurantOrder " +
                "and   restaurantCategory   = :restaurantCategory " +
                "and   restaurantCount      = :restaurantCount " +
                "and   restaurantStart      = :restaurantStart"
    )
    fun selectAllRestaurantsCache(
        restaurantEntityId: Int,
        restaurantEntityType: String,
        restaurantSort: String,
        restaurantOrder: String,
        restaurantCategory: Int,
        restaurantCount: Int,
        restaurantStart: Int
    ): Single<List<RestaurantCacheModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRestaurantsCache(restaurantCacheModel: List<RestaurantCacheModel>)
}