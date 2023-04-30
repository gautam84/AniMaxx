package com.example.animaxx.presentation.anime_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaxx.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailsViewModel @Inject constructor(
    private val repository: MainRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val animeName: String = checkNotNull(savedStateHandle["animeName"])

    private val _animeDetailState = mutableStateOf(AnimeDetailState())
    val animeDetailState: State<AnimeDetailState> = _animeDetailState

    init {

        viewModelScope.launch {
            repository.getAnimeFromKeyword(animeName).collect {
                _animeDetailState.value = animeDetailState.value.copy(
                    currAnime = it[0]
                )
            }
        }
    }

    fun onEvent(event: AnimeDetailEvent) {
        when (event) {
            is AnimeDetailEvent.ToggleLikedState -> {
                if (_animeDetailState.value.isFavourite) {
                    _animeDetailState.value = animeDetailState.value.copy(isFavourite = false)
                } else {
                    _animeDetailState.value = animeDetailState.value.copy(isFavourite = true)

                }
            }
        }
    }


}