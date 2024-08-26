package app.loococo.presentation.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen() {
    composable(route = splashRoute) {
        SplashRoute()
    }
}