package com.vlada.data.repositories

import androidx.paging.*
import com.vlada.data.database.dao.ChannelDao
import com.vlada.data.models.toDomain
import com.vlada.data.providers.ChannelProvider
import com.vlada.domain.models.Channel
import com.vlada.domain.repositories.ChannelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChannelRepositoryImpl @Inject constructor(
    private val channelDao: ChannelDao,
    private val channelProvider: ChannelProvider
) : ChannelRepository {

    override suspend fun searchChannels(query: String?): Flow<PagingData<Channel>> {
        ensureDatabaseLoaded()
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                channelDao
                    .searchChannels(query)
            }
        )
        val pagerFLow = pager.flow
            .map { pagingData ->
                pagingData.map { channel ->
                    channel.toDomain()
                }
            }
        return pagerFLow
    }

    private suspend fun ensureDatabaseLoaded() {
        if (channelDao.isEmpty()) {
            val channels = channelProvider.getChannels()
                .filterNotNull()
            channelDao.insertAll(channels)
        }
    }

}