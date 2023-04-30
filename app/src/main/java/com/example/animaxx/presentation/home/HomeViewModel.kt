package com.example.animaxx.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaxx.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _homeScreenState = mutableStateOf(
        HomeScreenState()
    )
    val homeScreenState: State<HomeScreenState> = _homeScreenState


    init {
        viewModelScope.launch {
            repository.getExploreList().collect{
                _homeScreenState.value = homeScreenState.value.copy(
                    exploreList = it
                )
            }

            repository.getTopRatedList().collect{
                _homeScreenState.value = homeScreenState.value.copy(
                    topRatedList = it
                )
            }
            repository.getRecentlySearchedList().collect{
                _homeScreenState.value = homeScreenState.value.copy(
                    recentlySearchedList = it
                )
            }
        }

    }
}