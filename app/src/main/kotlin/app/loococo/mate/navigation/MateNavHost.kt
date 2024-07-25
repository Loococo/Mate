package app.loococo.mate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.loococo.mate.ui.MateAppState
import app.loococo.presentation.login.loginRoute
import app.loococo.presentation.login.loginScreen
import app.loococo.presentation.login.navigateToLogin
import app.loococo.presentation.signup.navigateToSignUp
import app.loococo.presentation.signup.signUpScreen
import app.loococo.presentation.workspace.navigationToWorkspace
import app.loococo.presentation.workspace.workspaceScreen

@Composable
fun MateNavHost(
    appState: MateAppState,
    showToast: (String) -> Unit,
    startDestination: String = loginRoute
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginScreen(
            goToHome = { navController.navigationToWorkspace() },
            goToSignUp = { navController.navigateToSignUp() },
            showToast = showToast
        )

        signUpScreen(
            goToLogin = { navController.navigateToLogin() },
            showToast = showToast
        )

        workspaceScreen(showToast)
    }
}