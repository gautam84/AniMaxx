package com.example.animaxx.presentation.starred

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaxx.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarredViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _starredScreenState = mutableStateOf(StarredScreenState())
    val starredScreenState: State<StarredScreenState> = _starredScreenState

    init {
        viewModelScope.launch {
            repository.getStarredList().collect {
                _starredScreenState.value = starredScreenState.value.copy(
                    starredList = it
                )

            }
        }
    }
}