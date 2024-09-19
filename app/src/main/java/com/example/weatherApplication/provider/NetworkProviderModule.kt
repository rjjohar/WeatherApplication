package com.example.weatherApplication.provider

import com.example.data.api.IWeatherApiService
import com.example.data.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkProviderModule {

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder().client(
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        )
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun getNetworkService(): IWeatherApiService {
        return retrofit().create(IWeatherApiService::class.java)
    }
}