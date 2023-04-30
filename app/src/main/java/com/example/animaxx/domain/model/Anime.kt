package com.example.animaxx.domain.model

import androidx.annotation.DrawableRes

data class Anime(
    val name: String,
    val stars: Double,
    val genre: String,
    val actors: String,
    @DrawableRes val image: Int,
    val description: String
)
