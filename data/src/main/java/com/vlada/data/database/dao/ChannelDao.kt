package com.vlada.data.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.vlada.data.models.ChannelEntity

@Dao
interface ChannelDao {
    @Query(
        """
    SELECT * FROM ${ChannelEntity.ENTITY_NAME} 
    WHERE :searchQuery IS NULL 
    OR :searchQuery = '' 
    OR (
        ${ChannelEntity.TITLE_COLUMN_NAME} LIKE '%' || :searchQuery || '%' 
        OR ${ChannelEntity.CATEGORY_COLUMN_NAME} LIKE '%' || :searchQuery || '%'
    )
    """
    )
    fun searchChannels(searchQuery: String?): PagingSource<Int, ChannelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<ChannelEntity>)

    @Query("SELECT (SELECT COUNT(*) FROM ${ChannelEntity.ENTITY_NAME}) == 0")
    suspend fun isEmpty(): Boolean
}