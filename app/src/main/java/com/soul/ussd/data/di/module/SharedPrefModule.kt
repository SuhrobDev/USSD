package com.soul.ussd.data.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.soul.ussd.utils.SharedPref

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {

    @Provides
    fun provideShared(@ApplicationContext context: Context) = SharedPref(context)

}