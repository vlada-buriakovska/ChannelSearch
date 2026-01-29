package com.vlada.data.di

import com.vlada.data.repositories.ChannelRepositoryImpl
import com.vlada.domain.repositories.ChannelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindChannelRepository(
        channelRepository: ChannelRepository
    ): ChannelRepositoryImpl
}