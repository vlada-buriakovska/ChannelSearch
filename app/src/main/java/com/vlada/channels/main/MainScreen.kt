package com.vlada.channels.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadState.NotLoading
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.vlada.domain.models.Channel
import com.vlada.channels.R


@Composable
fun MainScreen(
    searchQuery: String?,
    pagingItems: LazyPagingItems<Channel>,
    onEvent: (MainUiEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            value = searchQuery ?: String(),
            onValueChange = {
                onEvent(MainUiEvent.OnQueryChanged(it))
            },
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1F)
                .padding(top = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            ChannelsList(pagingItems)

            val isListEmpty = pagingItems.itemSnapshotList.isEmpty()
            val isEndReached = pagingItems.loadState.append.endOfPaginationReached
            val isEmpty = pagingItems.loadState.refresh is NotLoading && isListEmpty && isEndReached

            if (isEmpty) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.all_empty),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            if (pagingItems.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ChannelsList(
    pagingItems: LazyPagingItems<Channel>,
) {
    LazyColumn {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { it.id }
        ) { index ->
            val item = pagingItems[index]
            item?.let {
                ChannelListItem(it)
            }
        }
        item {
            if (pagingItems.loadState.append is LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ChannelListItem(channel: Channel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1F)
                .padding(horizontal = 16.dp)
        ) {
            channel.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            channel.category?.value?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        if (channel.isLive == true) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .size(16.dp)
                    .background(color = Color.Red, shape = CircleShape)
            )
        }
    }
    HorizontalDivider()
}