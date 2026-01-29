package com.vlada.data.di

import android.app.Application
import androidx.room.Room
import com.vlada.data.database.AppDatabase
import com.vlada.data.database.dao.ChannelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    @Singleton
    fun provideChannelDao(context: Application): ChannelDao =
        provideDatabase(context).channelDao()
}