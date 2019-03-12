package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.github.viniciusalvesmello.domain.features.restaurants.GetCategoriesRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.GetRestaurantReviews
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview
import io.github.viniciusalvesmello.restaurantguide.AppApplication
import io.github.viniciusalvesmello.restaurantguide.BuildConfig
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.mapper.toRestaurantReviewView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantReviewView
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class RestaurantDetailsViewModel : ViewModel() {

    init {
        AppApplication.appComponent.inject(this)
    }

    companion object {
        private const val START = 0
        private const val COUNT = 50
    }

    @Inject
    lateinit var gerRestaurantReviews: GetRestaurantReviews

    private var _inProcessLoadRestaurantReviews = MutableLiveData<Boolean>()
    val inProcessLoadRestaurantReviews: LiveData<Boolean>
        get() = _inProcessLoadRestaurantReviews
    private val _listRestaurantReviews = MutableLiveData<List<RestaurantReviewView>>()
    val listRestaurantReviews: LiveData<List<RestaurantReviewView>>
        get() = _listRestaurantReviews
    private val _errorRestaurantDetailsViewModel = MutableLiveData<Throwable>()
    val errorRestaurantDetailsViewModel: LiveData<Throwable>
        get() = _errorRestaurantDetailsViewModel

    fun startLoadRestaurantReviews(restaurantId: Int) {
        _inProcessLoadRestaurantReviews.value = true
        loadRestaurantReviews(restaurantId = restaurantId)
    }

    private fun loadRestaurantReviews(restaurantId: Int) = gerRestaurantReviews.run(
        RestaurantReviewsSubscriber(),
        GetRestaurantReviews.Param(
            userKey = BuildConfig.ZomatoApiUserKey,
            restaurantId = restaurantId,
            count = COUNT,
            start = START
        )
    )

    inner class RestaurantReviewsSubscriber : DisposableSingleObserver<List<RestaurantReview>>() {
        override fun onSuccess(listRestaurantReviews: List<RestaurantReview>) {
            _listRestaurantReviews.value = listRestaurantReviews.map { restaurantReview ->
                restaurantReview.toRestaurantReviewView()
            }
            _inProcessLoadRestaurantReviews.value = false
        }

        override fun onError(error: Throwable) {
            _errorRestaurantDetailsViewModel.value = error
            _inProcessLoadRestaurantReviews.value = false
        }
    }

    fun disposable() {
        gerRestaurantReviews.dispose()
    }

}
