package com.vlada.domain.usecases

import com.vlada.domain.models.Channel
import com.vlada.domain.repositories.ChannelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GetChannelsUseCase @Inject constructor(private val channelRepository: ChannelRepository) {
    suspend fun invoke(): List<Channel> {
        return withContext(Dispatchers.IO) {
            channelRepository.getChannels()
        }
    }
}