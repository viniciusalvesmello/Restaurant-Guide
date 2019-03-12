package io.github.viniciusalvesmello.restaurantguide

import android.app.Application
import io.github.viniciusalvesmello.restaurantguide.di.AppComponent
import io.github.viniciusalvesmello.restaurantguide.di.AppModule
import io.github.viniciusalvesmello.restaurantguide.di.DaggerAppComponent

class AppApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }
}