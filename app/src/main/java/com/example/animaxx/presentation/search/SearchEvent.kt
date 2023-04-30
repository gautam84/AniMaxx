package com.example.animaxx.presentation.search

sealed class SearchEvent {
    data class ChangeText(val text: String) : SearchEvent()

}
