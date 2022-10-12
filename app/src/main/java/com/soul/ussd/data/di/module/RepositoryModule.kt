package com.soul.ussd.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.soul.ussd.data.services.remote.ServiceRepositoryImpl
import com.soul.ussd.data.services.remote.api.ServiceApi
import com.soul.ussd.data.ussd_codes.UssdCodesRepositoryImpl
import com.soul.ussd.data.ussd_codes.remote.api.UssdCodesApi
import com.soul.ussd.domain.services.ServiceRepository
import com.soul.ussd.domain.ussd_codes.UssdCodesRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideServiceRepository(loginApi: ServiceApi): ServiceRepository {
        return ServiceRepositoryImpl(loginApi)
    }

    @Provides
    fun provideUssdCodesRepository(loginApi: UssdCodesApi): UssdCodesRepository {
        return UssdCodesRepositoryImpl(loginApi)
    }

}