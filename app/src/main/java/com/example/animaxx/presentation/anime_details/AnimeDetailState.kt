package com.example.animaxx.presentation.anime_details

import com.example.animaxx.R
import com.example.animaxx.domain.model.Anime

data class AnimeDetailState(
    val currAnime: Anime = Anime("", 0.0, "", "", R.drawable.spy_family, ""),
    val isFavourite: Boolean = true

)