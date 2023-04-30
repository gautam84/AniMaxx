package com.example.animaxx.presentation.home

import com.example.animaxx.domain.model.Anime

data class HomeScreenState(
    val topRatedList: List<Anime> = listOf(),
    val recentlySearchedList: List<Anime> = listOf(),
    val exploreList: List<Anime> = listOf()

)
