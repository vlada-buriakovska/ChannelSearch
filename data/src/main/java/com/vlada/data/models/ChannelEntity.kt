package com.vlada.data.models

import com.google.gson.annotations.SerializedName
import com.vlada.domain.models.Channel

data class ChannelEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("category")
    val category: CategoryEntity?,
    @SerializedName("isLive")
    val isLive: Boolean?
)

fun ChannelEntity.toDomain(): Channel = Channel(
    id = this.id,
    title = this.title,
    category = this.category?.toDomain(),
    isLive = this.isLive
)