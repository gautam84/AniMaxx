package com.example.animaxx.presentation.starred

import com.example.animaxx.domain.model.Anime

data class StarredScreenState(
    val starredList: List<Anime> = mutableListOf()
)