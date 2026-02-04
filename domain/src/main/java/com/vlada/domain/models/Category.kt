package com.vlada.domain.models

data class ChannelCategory(
    val name: String?,
    val subCategory: ChannelCategory?
)