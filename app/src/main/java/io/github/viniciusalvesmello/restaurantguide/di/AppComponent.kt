package io.github.viniciusalvesmello.restaurantguide.di

import dagger.Component
import io.github.viniciusalvesmello.restaurantguide.AppApplication
import io.github.viniciusalvesmello.restaurantguide.di.features.restaurants.RestaurantsModule
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.RestaurantDetailsViewModel
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.RestaurantsViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RestaurantsModule::class
    ]
)
interface AppComponent {
    fun inject(application: AppApplication)
    fun inject(restaurantsViewModel: RestaurantsViewModel)
    fun inject(restaurantDetailsViewModel: RestaurantDetailsViewModel)
}