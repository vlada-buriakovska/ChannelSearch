package com.vlada.domain.models


enum class Category(val value: String) {
    SPORTS("Sports"),
    NEWS("News"),
    MUSIC("Music"),
    ENTERTAINMENT("Entertainment"),
    MOVIES("Movies"),
    KIDS("Kids");

    companion object {
        fun fromString(value: String): Category? =
            entries.find { it.value.equals(value, ignoreCase = true) }
    }
}