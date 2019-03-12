package io.github.viniciusalvesmello.restaurantguide.utils

import android.content.Context
import io.github.viniciusalvesmello.restaurantguide.utils.extension.isConnected
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkInformation @Inject constructor(private val context: Context) {
    val isConnected get() = context.isConnected()
}