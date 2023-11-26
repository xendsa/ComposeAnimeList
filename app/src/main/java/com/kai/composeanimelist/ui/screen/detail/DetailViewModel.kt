package com.kai.composeanimelist.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kai.composeanimelist.data.Repository
import com.kai.composeanimelist.model.AnimeDummy
import com.kai.composeanimelist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<AnimeDummy>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<AnimeDummy>>
        get() = _uiState

    fun getAnimeById(animeId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getAnimeById(animeId))
        }
    }
}