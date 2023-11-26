package com.kai.composeanimelist.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kai.composeanimelist.data.Repository
import com.kai.composeanimelist.model.AnimeDummy
import com.kai.composeanimelist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<AnimeDummy>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<AnimeDummy>>>
        get() = _uiState
//    private var animeList: List<AnimeDummy> = emptyList()


    fun getList() {
        viewModelScope.launch {
            repository.getAnime()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { animeList ->
                    _uiState.value = UiState.Success(animeList)
                }
        }
    }
//    fun searchAnime(query: String) {
//        val filteredList = if (query.isEmpty()) {
//            animeList
//        } else {
//            animeList.filter { anime ->
//                anime.anime.title.contains(query, true)
//            }
//        }
//
//        _uiState.value = UiState.Success(filteredList)
//    }
}