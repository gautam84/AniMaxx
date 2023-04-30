package com.example.animaxx.domain.repository

import com.example.animaxx.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getTopRatedList(): Flow<List<Anime>>
    suspend fun getRecentlySearchedList(): Flow<List<Anime>>
    suspend fun getExploreList(): Flow<List<Anime>>
    suspend fun getAnimeFromKeyword(keyword: String): Flow<List<Anime>>
    suspend fun getStarredList(): Flow<List<Anime>>
    val isAppDark: Flow<Boolean>
    suspend fun toggleLoginState()
}