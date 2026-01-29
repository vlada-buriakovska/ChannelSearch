package com.vlada.channels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vlada.domain.usecases.SearchChannelsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val searchChannelsUseCase: SearchChannelsUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow<String?>(null)
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val channelFlow = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            searchChannelsUseCase.invoke(SearchChannelsUseCase.Params(query))
        }
        .cachedIn(viewModelScope)

    fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.OnQueryChanged -> updateQuery(event.query)
        }
    }

    private fun updateQuery(newQuery: String?) {
        _searchQuery.value = newQuery
    }
}

sealed class MainUiEvent {
    data class OnQueryChanged(val query: String?) : MainUiEvent()
}