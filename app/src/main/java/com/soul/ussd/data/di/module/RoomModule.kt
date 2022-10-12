//package com.soul.ussd.data.di.module
//
//import android.app.Application
//import androidx.room.Room
//import com.soul.ussd.utils.Constants
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object RoomModule {
//
//    @Singleton
//    @Provides
//    fun providesAppDatabase(application: Application) = Room.databaseBuilder(
//        application.applicationContext, AppDatabase::class.java, Constants.DATABASE_NAME
//    )
////        .allowMainThreadQueries()
////        .fallbackToDestructiveMigration()
////        .addMigrations(Migrations.isBonusTypeMigration)
//        .build()
//
//    @Singleton
//    @Provides
//    fun providesCardDao(appDatabase: AppDatabase) = appDatabase.getCardDao()
//
//}