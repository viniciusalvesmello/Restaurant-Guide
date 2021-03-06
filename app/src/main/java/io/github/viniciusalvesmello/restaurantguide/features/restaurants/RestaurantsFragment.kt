package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import io.fabric.sdk.android.Fabric
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.CategoryRestaurantsView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.onBackPressed
import io.github.viniciusalvesmello.restaurantguide.utils.extension.toCityView
import kotlinx.android.synthetic.main.restaurants_fragment.*

class RestaurantsFragment : Fragment() {

    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProviders.of(this).get(RestaurantsViewModel::class.java)
    }
    private lateinit var cityView: CityView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fabric.with(context, Crashlytics())
        cityView = arguments?.toCityView() ?: CityView.toEmpty()
        return inflater.inflate(R.layout.restaurants_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateActionBar()
        updateCategoriesAndRestaurants()
    }

    private fun updateActionBar() {
        constraint_layout_back_pressed.setOnClickListener {
            it.onBackPressed()
        }
        val textCityName =
            getString(R.string.name_city_in) + " " + cityView.name + getString(R.string.name_city_made_for_you)
        text_view_action_bar_city_name.text = textCityName
    }

    private fun updateCategoriesAndRestaurants() {
        viewModel.inProcessLoadRestaurants.observe(this, Observer { inProcessLoadRestaurants ->
            if (inProcessLoadRestaurants) showProgressBar()
            else hideProgressBar()
        })
        chip_group_list_category.setOnCheckedChangeListener { _, chipId ->
            viewModel.setCategoryRestaurants(chipId)
            viewModel.startLoadRestaurants(false)
        }
        viewModel.listCategoriesRestaurants.observe(this, Observer { listRestaurantsCategories ->
            listRestaurantsCategories.forEach { categoryRestaurantsView ->
                chip_group_list_category.addView(createChip(categoryRestaurantsView))
            }
        })
        viewModel.listRestaurants.observe(this, Observer { listRestaurants ->
            recycle_view_restaurants.adapter = RestaurantsAdapter(
                listRestaurants,
                viewModel
            )
            recycle_view_restaurants.layoutManager = LinearLayoutManager(context)
            hideProgressBar()
        })
        viewModel.errorRestaurantsViewModel.observe(this, Observer { error ->
            showError(error)
        })
        viewModel.setCityId(cityView.id)
        chip_group_list_category.addView(createChip(CategoryRestaurantsView(id = 0, name = getString(R.string.all))))
        viewModel.startLoadRestaurants()
    }

    private fun createChip(categoryRestaurantsView : CategoryRestaurantsView) : Chip {
        val chip = Chip(context)
        chip.id = categoryRestaurantsView.id
        chip.text = categoryRestaurantsView.name
        chip.isClickable = true
        chip.isCheckable = true
        chip.isChecked = (viewModel.categoryRestaurants == chip.id)
        return chip
    }

    private fun showError(error : Throwable) {
        Snackbar.make(
            coordinator_layout_snackbar,
            getString(R.string.we_were_unable_to_process_your_request),
            Snackbar.LENGTH_LONG
        ).show()
        hideProgressBar()
        Crashlytics.logException(error)
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable()
    }
}
