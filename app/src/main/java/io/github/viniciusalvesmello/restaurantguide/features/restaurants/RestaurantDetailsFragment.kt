package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import io.fabric.sdk.android.Fabric
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.databinding.RestaurantDetailsFragmentBinding
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.onBackPressed
import io.github.viniciusalvesmello.restaurantguide.utils.extension.toRestaurantView
import kotlinx.android.synthetic.main.restaurant_details_fragment.*

class RestaurantDetailsFragment : Fragment() {

    private val viewModel: RestaurantDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(RestaurantDetailsViewModel::class.java)
    }
    private lateinit var restaurantView: RestaurantView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fabric.with(context, Crashlytics())
        restaurantView = arguments?.toRestaurantView() ?: RestaurantView.toEmpty()
        val binding : RestaurantDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.restaurant_details_fragment, container, false
        )
        binding.restaurant = restaurantView
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        constraint_layout_back_pressed.setOnClickListener {
            it.onBackPressed()
        }
        viewModel.errorRestaurantDetailsViewModel.observe(this, Observer { error ->
            showError(error)
        })
        viewModel.startLoadRestaurantReviews(restaurantView.id)
    }

    private fun showError(error : Throwable) {
        Snackbar.make(
            coordinator_layout_snackbar,
            getString(R.string.we_were_unable_to_process_your_request),
            Snackbar.LENGTH_LONG
        ).show()
        progress_bar.visibility = View.GONE
        Crashlytics.logException(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable()
    }
}
