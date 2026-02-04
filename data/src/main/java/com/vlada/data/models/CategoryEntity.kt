package com.vlada.data.models

import com.google.gson.annotations.SerializedName
import com.vlada.domain.models.ChannelCategory

data class CategoryEntity(
    @SerializedName("name")
    val name: String?,
    @SerializedName("subCategory")
    val subCategory: CategoryEntity?,
)

fun CategoryEntity.toDomain(): ChannelCategory = ChannelCategory(
    name = this.name,
    subCategory = this.subCategory?.toDomain(),
)