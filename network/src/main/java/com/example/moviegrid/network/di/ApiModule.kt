package com.example.moviegrid.network.di

import com.example.moviegrid.core.App
import com.example.moviegrid.network.BuildConfig
import com.example.moviegrid.network.api.ApiMoviesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object ApiModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideGson(): Gson {
        return GsonBuilder().setPrettyPrinting()
            .setDateFormat(GSON_DATE_FORMAT)
            .setLenient()
            .create()
    }


    @Provides
    @Singleton
    @JvmStatic
    fun provideConvertersFactory(gson: Gson): Converter.Factory =
        GsonConverterFactory.create(gson)


    @Provides
    @Singleton
    @JvmStatic
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttpClient(
        app: App,
        interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val cache = Cache(app.getApplicationContext().cacheDir, CACHE_SIZE)
        return OkHttpClient().newBuilder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .cache(cache)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofitBuilder(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit.Builder = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(converterFactory)
        .client(client)


    @Provides
    @Singleton
    @JvmStatic
    fun provideApiProfileService(
        builder: Retrofit.Builder
    ): ApiMoviesService = builder
        .baseUrl(BuildConfig.PRODUCTION_ENDPOINT)
        .build()
        .create(ApiMoviesService::class.java)

    //private const val BASE_URL = BuildConfig.BASE_URL
    private const val CACHE_SIZE: Long = 10 * 1024 * 1024 // 10MB
    private const val GSON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private const val CONNECTION_TIMEOUT = 120L
    private const val READ_TIMEOUT = 120L
    private const val WRITE_TIMEOUT = 120L
}