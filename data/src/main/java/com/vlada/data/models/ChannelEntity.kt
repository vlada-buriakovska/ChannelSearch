package com.vlada.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vlada.data.models.ChannelEntity.Companion.ENTITY_NAME
import com.vlada.domain.models.Category
import com.vlada.domain.models.Channel

@Entity(tableName = ENTITY_NAME)
data class ChannelEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("isLive")
    val isLive: Boolean?
) {
    companion object {
        const val ENTITY_NAME = "movies"
        const val TITLE_COLUMN_NAME = "title"
        const val CATEGORY_COLUMN_NAME = "category"
    }
}

fun ChannelEntity.toDomain(): Channel = Channel(
    id = this.id,
    title = this.title,
    category = this.category?.let { Category.fromString(it) },
    isLive = this.isLive
)