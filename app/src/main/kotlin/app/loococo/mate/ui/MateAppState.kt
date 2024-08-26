package app.loococo.mate.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.loococo.domain.model.state.MainUiState
import app.loococo.presentation.login.loginRoute
import app.loococo.presentation.login.navigateToLogin
import app.loococo.presentation.splash.splashRoute
import app.loococo.presentation.workspace.workspaceRoute

@Composable
fun rememberMateAppState(
    navController: NavHostController = rememberNavController(),
    uiState: MainUiState,
    logoutState: Boolean
): MateAppState {
    return remember(navController, uiState, logoutState) {
        MateAppState(
            navController = navController,
            uiState = uiState,
            logoutState = logoutState
        )
    }
}

@Stable
class MateAppState(
    val navController: NavHostController,
    val uiState: MainUiState,
    val logoutState: Boolean
) {

    val startDestination = when (uiState) {
//        MainUiState.Loading -> splashRoute
//        is MainUiState.Success -> {
//            if (uiState.user == null) {
//                loginRoute
//            } else {
//                workspaceRoute
//            }
//        }
        MainUiState.Splash -> splashRoute
        MainUiState.Login -> loginRoute
        MainUiState.Workspace -> workspaceRoute
    }
//    val startDestination = when (uiState) {
//        MainUiState.Workspace -> workspaceRoute
//        MainUiState.Login -> loginRoute
//    }

    fun handleNavigation() {
        if (logoutState) {
            navController.navigateToLogin()
        }
    }
}