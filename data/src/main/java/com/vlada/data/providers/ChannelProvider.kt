package com.vlada.data.providers

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vlada.data.models.ChannelEntity


class ChannelProvider(
    private val context: Context,
    private val gson: Gson
) {
    companion object Companion {
        const val CHANNELS_ASSETS_FILE_NAME = "channels.json"
    }

    fun getChannels(): List<ChannelEntity?> {
        val channelsString = context.assets.open(CHANNELS_ASSETS_FILE_NAME)
            .bufferedReader()
            .use { it.readText() }
        val listType = object : TypeToken<List<ChannelEntity?>?>() {}.type
        return gson.fromJson(channelsString, listType)
    }
}