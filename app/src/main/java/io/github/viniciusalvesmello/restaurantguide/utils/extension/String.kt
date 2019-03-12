package io.github.viniciusalvesmello.restaurantguide.utils.extension

import android.content.Context
import android.widget.Toast

fun String.toastLong(context: Context?) {
    Toast.makeText(
        context,
        this,
        Toast.LENGTH_LONG
    ).show()
}