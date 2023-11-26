package com.kai.composeanimelist.ui.nav

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailAnime : Screen("home/{animeId}"){
        fun createRoute(animeId: Long) = "home/$animeId"
    }
}