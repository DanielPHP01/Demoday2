package com.dipumba.movies.android.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dipumba.movies.android.presentation.objects.Screen
import com.dipumba.movies.android.presentation.screens.home.Home
import com.dipumba.movies.android.presentation.screens.detail.Detail

@Composable
fun Navigation(
    context: Context
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            Home(context, navigationVerified = {navController.navigate(Screen.VerifiedScreen.route)})
        }
        composable(Screen.VerifiedScreen.route ) {
            Detail()
        }
    }
}
