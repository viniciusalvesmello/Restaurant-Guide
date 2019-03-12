package io.github.viniciusalvesmello.restaurantguide.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.viniciusalvesmello.cache.AppDatabase
import io.github.viniciusalvesmello.restaurantguide.AppApplication
import io.github.viniciusalvesmello.restaurantguide.BuildConfig
import io.github.viniciusalvesmello.restaurantguide.utils.extension.isConnected
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.*
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule(private val application: AppApplication) {

    companion object {
        private const val TIME_OUT_30_SECONDS = 30.toLong()
        private const val CACHE_SIZE_5_MEGA_BYTES = (5 * 1024 * 1024).toLong()
        private const val CACHE_TIME_ON_LINE_ONE_HOUR_IN_SECONDS = (60 * 60)
        private const val CACHE_OFF_LINE_SEVEN_DAYS_IN_SECONDS = (60 * 60 * 24 * 7)
        private const val DB_NAME = "rgcache.db"
    }

    @Provides
    @Singleton
    fun provideAppApplicationContext(): Context = application

    @Provides
    @Named("SchedulersIO")
    fun provideSchedulersIO(): Scheduler = Schedulers.io()

    @Provides
    @Named("AndroidSchedulersMainThread")
    fun provideAndroidSchedulersMainThread(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Named("CompositeDisposable")
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ZomatoApiUrl)
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_30_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_30_SECONDS, TimeUnit.SECONDS)
            .cache(Cache(application.cacheDir, CACHE_SIZE_5_MEGA_BYTES))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (application.isConnected())
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=$CACHE_TIME_ON_LINE_ONE_HOUR_IN_SECONDS"
                    ).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$CACHE_OFF_LINE_SEVEN_DAYS_IN_SECONDS"
                    ).build()
                chain.proceed(request)
            }
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DB_NAME
    ).build()

}