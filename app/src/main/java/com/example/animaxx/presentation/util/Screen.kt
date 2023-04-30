package com.example.animaxx.presentation.util

sealed class Screen(val route: String) {
    object AboutScreen : Screen(route = "about")
    object AnimeDetailScreen : Screen(route = "anime_detail")
    object HomeScreen : Screen(route = "home")
    object SearchScreen : Screen(route = "search")
    object StarredScreen : Screen(route = "starred")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
