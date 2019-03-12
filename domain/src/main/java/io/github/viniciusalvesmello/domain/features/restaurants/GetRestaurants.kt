package io.github.viniciusalvesmello.domain.features.restaurants

import io.github.viniciusalvesmello.domain.features.restaurants.GetRestaurants.Param
import io.github.viniciusalvesmello.domain.SingleUseCase
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class GetRestaurants
@Inject constructor(
    private val restaurantsRepository: RestaurantsRepository,
    @Named("SchedulersIO") schedulerIO: Scheduler,
    @Named("AndroidSchedulersMainThread") androidSchedulersMainThread: Scheduler,
    @Named("CompositeDisposable") compositeDisposable: CompositeDisposable
) : SingleUseCase<List<Restaurant>, Param>(
    schedulerIO,
    androidSchedulersMainThread,
    compositeDisposable
) {

    override fun buildSingleUseCaseObservable(params: Param): Single<List<Restaurant>> =
        restaurantsRepository.getRestaurants(
            userKey = params.userKey,
            entityId = params.entityId,
            entityType = params.entityType,
            sort = params.sort,
            order = params.order,
            category = params.category,
            count = params.count,
            start = params.start
        )

    data class Param(
        val userKey: String,
        val entityId: Int,
        val entityType: String,
        val sort: String,
        val order: String,
        val category: Int,
        val count: Int,
        val start: Int
    )
}