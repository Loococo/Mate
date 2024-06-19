package app.loococo.mate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.loococo.mate.ui.MateAppState
import app.loococo.presentation.login.loginRoute
import app.loococo.presentation.login.loginScreen

@Composable
fun MateNavHost(
    appState: MateAppState
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = loginRoute
    ) {
        loginScreen()
    }
}