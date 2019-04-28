package io.github.viniciusalvesmello.restaurantguide.utils.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurantguide.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(appCompatImageView: AppCompatImageView, url: String) {
        if (url.isEmpty())
            appCompatImageView.setImageResource(R.drawable.no_image)
        else
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(appCompatImageView)
    }

}