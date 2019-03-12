package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.viniciusalvesmello.domain.features.restaurants.GetCategoriesRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.GetRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.github.viniciusalvesmello.restaurantguide.AppApplication
import io.github.viniciusalvesmello.restaurantguide.BuildConfig
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.mapper.toCategoryRestaurantsView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.mapper.toRestaurantView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.CategoryRestaurantsView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class RestaurantsViewModel : ViewModel() {

    init {
        AppApplication.appComponent.inject(this)
    }

    companion object {
        private const val ENTITY_TYPE = "city"
        private const val SORT = "rating"
        private const val ORDER = "desc"
        private const val COUNT = 10
    }

    @Inject
    lateinit var getCategoriesRestaurants: GetCategoriesRestaurants

    @Inject
    lateinit var getRestaurants: GetRestaurants

    private var _inProcessLoadRestaurants = MutableLiveData<Boolean>()
    val inProcessLoadRestaurants: LiveData<Boolean>
        get() = _inProcessLoadRestaurants
    private var _cityId = 0
    val cityId: Int
        get() = _cityId
    private var _categoryRestaurants = 0
    val categoryRestaurants: Int
        get() = _categoryRestaurants
    private var _start = 0
    val start: Int
        get() = _start
    private val _listCategoriesRestaurants = MutableLiveData<List<CategoryRestaurantsView>>()
    val listCategoriesRestaurants: LiveData<List<CategoryRestaurantsView>>
        get() = _listCategoriesRestaurants
    private val _listRestaurants = MutableLiveData<List<RestaurantView>>()
    val listRestaurants: LiveData<List<RestaurantView>>
        get() = _listRestaurants
    private val _errorRestaurantsViewModel = MutableLiveData<Throwable>()
    val errorRestaurantsViewModel: LiveData<Throwable>
        get() = _errorRestaurantsViewModel

    fun setCityId(setValue: Int) {
        if (_cityId != setValue) {
            _cityId = setValue
            clearStart()
        }
    }

    fun setCategoryRestaurants(setValue: Int) {
        if (_categoryRestaurants != setValue) {
            _categoryRestaurants = setValue
            clearStart()
        }
    }

    fun clearStart() {
        _start = 0
    }

    fun updateLastStart() {
        _start -= COUNT
    }

    fun updateNextStart() {
        _start += COUNT
    }

    fun startLoadRestaurants(loadCategories: Boolean = true) {
        _inProcessLoadRestaurants.value = true
        if (loadCategories) loadCategoriesRestaurants()
        loadRestaurants()
    }

    private fun loadCategoriesRestaurants() {
        if (_listCategoriesRestaurants.value.isNullOrEmpty())
            getCategoriesRestaurants.run(
                CategoriesSubscriber(),
                GetCategoriesRestaurants.Param(BuildConfig.ZomatoApiUserKey)
            )
    }

    inner class CategoriesSubscriber : DisposableSingleObserver<List<CategoryRestaurants>>() {
        override fun onSuccess(listCategoriesRestaurants: List<CategoryRestaurants>) {
            _listCategoriesRestaurants.value = listCategoriesRestaurants.map { category ->
                category.toCategoryRestaurantsView()
            }
        }

        override fun onError(error: Throwable) {
            _errorRestaurantsViewModel.value = error
        }

    }

    private fun loadRestaurants() = getRestaurants.run(
        RestaurantsSubscriber(),
        GetRestaurants.Param(
            userKey = BuildConfig.ZomatoApiUserKey,
            entityId = cityId,
            entityType = ENTITY_TYPE,
            sort = SORT,
            order = ORDER,
            category = categoryRestaurants,
            count = COUNT,
            start = start
        )
    )

    inner class RestaurantsSubscriber : DisposableSingleObserver<List<Restaurant>>() {
        override fun onSuccess(listRestaurants: List<Restaurant>) {
            _listRestaurants.value = listRestaurants.map { restaurant ->
                restaurant.toRestaurantView()
            }
            _inProcessLoadRestaurants.value = false
        }

        override fun onError(error: Throwable) {
            _errorRestaurantsViewModel.value = error
            _inProcessLoadRestaurants.value = false
        }
    }


    fun disposable() {
        getCategoriesRestaurants.dispose()
        getRestaurants.dispose()
    }
}