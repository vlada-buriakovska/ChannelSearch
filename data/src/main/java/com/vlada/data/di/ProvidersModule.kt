package com.vlada.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vlada.data.providers.ChannelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidersModule {

    @Provides
    @Singleton
    fun provideChannelProvider(
        @ApplicationContext
        appContext: Context, gson: Gson
    ): ChannelProvider =
        ChannelProvider(appContext, gson)
    
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }
}