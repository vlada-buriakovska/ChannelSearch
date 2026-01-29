package com.vlada.channels.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.vlada.channels.ui.theme.ChannelSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainVM: MainVM = hiltViewModel()
            ChannelSearchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        val pagingItems = mainVM.channelFlow.collectAsLazyPagingItems()
                        val searchQuery by mainVM.searchQuery.collectAsState()
                        MainScreen(
                            searchQuery = searchQuery,
                            pagingItems = pagingItems,
                            onEvent = mainVM::onEvent
                        )
                    }
                }
            }
        }
    }
}