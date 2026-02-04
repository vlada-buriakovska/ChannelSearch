package com.vlada.domain.models

data class Channel(
    val id: String,
    val title: String?,
    val category: ChannelCategory?,
    val isLive: Boolean?
)