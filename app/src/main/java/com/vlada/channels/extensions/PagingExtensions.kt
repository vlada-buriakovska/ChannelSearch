package com.vlada.channels.extensions

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems


fun <T : Any> LazyPagingItems<T>.errorMessage(): String? {
    return when {
        loadState.refresh is LoadState.Error -> {
            val e = loadState.refresh as LoadState.Error
            e.error.localizedMessage
        }

        loadState.append is LoadState.Error -> {
            val e = loadState.append as LoadState.Error
            e.error.localizedMessage
        }

        else -> {
            null
        }
    }
}