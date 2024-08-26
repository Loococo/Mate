package app.loococo.mate.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import app.loococo.mate.ui.MateAppState
import app.loococo.presentation.login.loginRoute
import app.loococo.presentation.login.loginScreen
import app.loococo.presentation.login.navigateToLogin
import app.loococo.presentation.signup.navigateToSignUp
import app.loococo.presentation.signup.signUpScreen
import app.loococo.presentation.splash.splashScreen
import app.loococo.presentation.workspace.navigationToWorkspace
import app.loococo.presentation.workspace.workspaceScreen

@Composable
fun MateNavHost(
    appState: MateAppState,
    showToast: (String) -> Unit
) {
    val navController = appState.navController

//    LaunchedEffect(appState.logoutState) {
//        appState.handleNavigation()
//    }

    NavHost(
        navController = navController,
        startDestination = appState.startDestination
    ) {
        splashScreen()

        loginScreen(
            goToHome = {
                navController.navigationToWorkspace() },
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