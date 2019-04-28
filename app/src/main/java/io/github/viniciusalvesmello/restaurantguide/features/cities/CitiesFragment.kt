package io.github.viniciusalvesmello.restaurantguide.features.cities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import io.github.viniciusalvesmello.restaurantguide.R
import kotlinx.android.synthetic.main.cities_fragment.*
import io.fabric.sdk.android.Fabric
import io.github.viniciusalvesmello.restaurantguide.databinding.CitiesFragmentBinding


class CitiesFragment : Fragment() {

    private val viewModel: CitiesViewModel by lazy {
        ViewModelProviders.of(this).get(CitiesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fabric.with(context, Crashlytics())
        val binding : CitiesFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.cities_fragment, container, false
        )
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadCities()
        /*showProgressBar()
        viewModel.listCities.observe(this, Observer { listCityView ->
            recycle_view_cities.adapter = CitiesAdapter(listCityView)
            recycle_view_cities.layoutManager = LinearLayoutManager(context)
            hideProgressBar()
        })*/
    }

    /*fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }*/

}
