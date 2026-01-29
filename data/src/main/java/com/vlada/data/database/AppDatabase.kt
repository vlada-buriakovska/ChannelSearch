package com.vlada.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlada.data.database.dao.ChannelDao
import com.vlada.data.models.ChannelEntity

@Database(
    entities = [ChannelEntity::class],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "channel_search.db"
    }

    abstract fun channelDao(): ChannelDao

}