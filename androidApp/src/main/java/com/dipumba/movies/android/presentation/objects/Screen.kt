package com.dipumba.movies.android.presentation.objects

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object VerifiedScreen : Screen("verified_screen")

    val routeDetailWithArgs: String
        get() = "${VerifiedScreen.route}/movieId"

    val argument = listOf(
        navArgument(name="movieId"){type = NavType.StringType}
    )
}

