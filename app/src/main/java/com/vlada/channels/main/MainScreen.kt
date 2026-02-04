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
import androidx.compose.ui.unit.dp
import com.vlada.domain.models.Channel
import com.vlada.domain.models.ChannelCategory


@Composable
fun MainScreen(
    channelsList: List<Channel>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        ChannelsList(channelsList)
    }
}

@Composable
fun ChannelsList(
    list: List<Channel>,
) {
    LazyColumn {
        items(
            count = list.size,
            key = { list[it].id }
        ) { index ->
            val item = list[index]
            ChannelListItem(item)
        }
        item {
            //TODO
            // implement paging loading
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
            channel.category?.let { CategoryItem(it) }
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

@Composable
fun CategoryItem(category: ChannelCategory) {
    category.name?.let {
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = it,
            style = MaterialTheme.typography.bodyLarge
        )
    }
    category.subCategory?.let {
        CategoryItem(it)
    }
}