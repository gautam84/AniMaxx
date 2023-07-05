/**
 *
 *	MIT License
 *
 *	Copyright (c) 2023 Gautam Hazarika
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in all
 *	copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *	SOFTWARE.
 *
 **/

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