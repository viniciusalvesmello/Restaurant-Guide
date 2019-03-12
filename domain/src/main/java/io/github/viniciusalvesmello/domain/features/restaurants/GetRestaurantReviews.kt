package io.github.viniciusalvesmello.domain.features.restaurants

import io.github.viniciusalvesmello.domain.SingleUseCase
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class GetRestaurantReviews
@Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    @Named("SchedulersIO") schedulerIO: Scheduler,
    @Named("AndroidSchedulersMainThread") androidSchedulersMainThread: Scheduler,
    @Named("CompositeDisposable") compositeDisposable: CompositeDisposable
) : SingleUseCase<List<RestaurantReview>, GetRestaurantReviews.Param>(
    schedulerIO,
    androidSchedulersMainThread,
    compositeDisposable
) {
    override fun buildSingleUseCaseObservable(params: Param): Single<List<RestaurantReview>> =
        restaurantsRepository.getRestaurantReviews(
            userKey = params.userKey,
            restaurantId = params.restaurantId,
            count = params.count,
            start = params.start
        )

    data class Param(
       val userKey: String,
       val restaurantId : Int,
       val count: Int,
       val start: Int
    )
}