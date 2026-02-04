package com.vlada.channels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlada.domain.models.Channel
import com.vlada.domain.usecases.GetChannelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getChannelsUseCase: GetChannelsUseCase,
) : ViewModel() {

    private val _channelsList = MutableStateFlow<List<Channel>>(emptyList())
    val channelsList = _channelsList.asStateFlow()
    
    init {
        getChannels()
    }

    private fun getChannels() {
        viewModelScope.launch {
            _channelsList.value = getChannelsUseCase.invoke()
        }
    }
}