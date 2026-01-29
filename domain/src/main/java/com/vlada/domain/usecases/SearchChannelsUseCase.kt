package com.vlada.domain.usecases

import com.vlada.domain.models.Channel
import com.vlada.domain.repositories.ChannelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import androidx.paging.PagingData
import javax.inject.Inject


class SearchChannelsUseCase @Inject constructor(private val channelRepository: ChannelRepository) {
    suspend fun invoke(params: Params): Flow<PagingData<Channel>> {
        return withContext(Dispatchers.IO) {
            channelRepository.searchChannels(params.query)
        }
    }

    class Params(
        val query: String?,
    )
}