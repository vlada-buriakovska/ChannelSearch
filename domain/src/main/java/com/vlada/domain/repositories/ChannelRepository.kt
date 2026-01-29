package com.vlada.domain.repositories

import androidx.paging.PagingData
import com.vlada.domain.models.Channel
import kotlinx.coroutines.flow.Flow


interface ChannelRepository {
    suspend fun searchChannels(query: String?): Flow<PagingData<Channel>>
}