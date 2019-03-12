package io.github.viniciusalvesmello.restaurantguide.di.features.restaurants

import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.cache.AppDatabase
import io.github.viniciusalvesmello.cache.features.restaurants.RestaurantsCacheImplemantation
import io.github.viniciusalvesmello.data.features.restaurants.DataRestaurantsRepository
import io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface.RestaurantsCache
import io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface.RestaurantsRemote
import io.github.viniciusalvesmello.domain.features.restaurants.RestaurantsRepository
import io.github.viniciusalvesmello.remote.ZomatoApiService
import io.github.viniciusalvesmello.remote.features.restaurants.RestaurantsRemoteImplemantation
import javax.inject.Singleton

@Module
class RestaurantsModule {

    @Provides
    @Singleton
    fun provideRestaurantsRemoteImplemantation(zomatoApiService: ZomatoApiService): RestaurantsRemote {
        return RestaurantsRemoteImplemantation(zomatoApiService)
    }

    @Provides
    @Singleton
    fun provideRestaurantsCacheImplemantation(appDatabase: AppDatabase): RestaurantsCache {
        return RestaurantsCacheImplemantation(
            appDatabase.categoryRestaurantsCacheDao(),
            appDatabase.restaurantCacheDao(),
            appDatabase.restaurantReviewCacheDao()
        )
    }

    @Provides
    @Singleton
    fun provideDataRestaurantsRepository(
        restaurantsRemote: RestaurantsRemote,
        restaurantsCache: RestaurantsCache
    ): RestaurantsRepository {
        return DataRestaurantsRepository(restaurantsRemote, restaurantsCache)
    }

}