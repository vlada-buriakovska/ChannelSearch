package com.vlada.data.repositories

import com.vlada.data.models.toDomain
import com.vlada.data.providers.ChannelProvider
import com.vlada.domain.models.Channel
import com.vlada.domain.repositories.ChannelRepository
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val channelProvider: ChannelProvider
) : ChannelRepository {

    override suspend fun getChannels(): List<Channel> {
        val channels = channelProvider.getChannels()
            .filterNotNull()
        return channels.map { it.toDomain() }
    }
}