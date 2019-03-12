package io.github.viniciusalvesmello.domain.features.restaurants

import io.github.viniciusalvesmello.domain.SingleUseCase
import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class GetCategoriesRestaurants
@Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    @Named("SchedulersIO") schedulerIO: Scheduler,
    @Named("AndroidSchedulersMainThread") androidSchedulersMainThread: Scheduler,
    @Named("CompositeDisposable") compositeDisposable: CompositeDisposable
) : SingleUseCase<List<CategoryRestaurants>, GetCategoriesRestaurants.Param>(
    schedulerIO,
    androidSchedulersMainThread,
    compositeDisposable
) {
    override fun buildSingleUseCaseObservable(params: GetCategoriesRestaurants.Param): Single<List<CategoryRestaurants>>  =
        restaurantsRepository.getCategoriesRestaurants(userKey = params.userKey)

    data class Param(val userKey: String)
}