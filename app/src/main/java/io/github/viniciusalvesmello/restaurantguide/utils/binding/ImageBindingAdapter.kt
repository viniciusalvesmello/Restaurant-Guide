package io.github.viniciusalvesmello.restaurantguide.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurantguide.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(imageView: ImageView, url: String) {
        if (url.isEmpty())
            imageView.setImageResource(R.drawable.no_image)
        else
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(imageView)
    }

}