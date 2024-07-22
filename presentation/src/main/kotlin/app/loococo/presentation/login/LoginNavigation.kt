package app.loococo.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen(
    goToHome: () -> Unit,
    goToSignUp: () -> Unit,
    showToast: (String) -> Unit
) {
    composable(route = loginRoute) {
        LoginRoute(goToHome, goToSignUp, showToast)
    }
}
