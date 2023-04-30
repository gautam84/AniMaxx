package com.example.animaxx.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaxx.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _searchScreenState = mutableStateOf(SearchState())
    val searchScreenState: State<SearchState> = _searchScreenState

    init {
        viewModelScope.launch {
            repository.getRecentlySearchedList().collect{
                _searchScreenState.value = searchScreenState.value.copy(
                    recentlySearchedList = it
                )

            }
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.ChangeText -> {
                _searchScreenState.value = searchScreenState.value.copy(
                    text = event.text
                )
                viewModelScope.launch {
                    repository.getAnimeFromKeyword(_searchScreenState.value.text).collect {
                        _searchScreenState.value = searchScreenState.value.copy(
                            searchedList = it
                        )
                    }
                }
            }


        }
    }
}