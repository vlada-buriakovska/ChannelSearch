package com.vlada.domain.repositories

import com.vlada.domain.models.Channel


interface ChannelRepository {
    suspend fun getChannels(): List<Channel>
}