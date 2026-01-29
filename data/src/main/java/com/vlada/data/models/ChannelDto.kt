package com.vlada.data.models

import com.google.gson.annotations.SerializedName
import com.vlada.domain.models.Category
import com.vlada.domain.models.Channel

data class ChannelDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("isLive")
    val isLive: Boolean?
)

fun ChannelDto.toDomain(): Channel = Channel(
    id = this.id,
    title = this.title,
    category = this.category?.let { Category.fromString(it) },
    isLive = this.isLive
)