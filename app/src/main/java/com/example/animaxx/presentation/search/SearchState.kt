package com.example.animaxx.presentation.search

import com.example.animaxx.domain.model.Anime

data class SearchState(
    val text: String = "",
    val searchedList: List<Anime> = mutableListOf(),
    val recentlySearchedList: List<Anime> = mutableListOf(),

    )