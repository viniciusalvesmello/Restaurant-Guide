package io.github.viniciusalvesmello.restaurantguide.utils

import androidx.navigation.navOptions
import io.github.viniciusalvesmello.restaurantguide.R
import javax.inject.Singleton

@Singleton
class Animation {
    companion object {
        fun transitionFragment() = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
    }
}