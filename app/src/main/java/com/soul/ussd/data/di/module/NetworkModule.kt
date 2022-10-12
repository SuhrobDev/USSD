package com.soul.ussd.data.di.module

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.soul.ussd.utils.Constants
import com.soul.ussd.data.services.remote.api.ServiceApi
import com.soul.ussd.data.ussd_codes.remote.api.UssdCodesApi


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL)
            client(OkHttpClient.Builder().addNetworkInterceptor(httpLoggingInterceptor).build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Provides
    fun provideUssdApi(retrofit: Retrofit): UssdCodesApi {
        return retrofit.create(UssdCodesApi::class.java)
    }


    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }
}