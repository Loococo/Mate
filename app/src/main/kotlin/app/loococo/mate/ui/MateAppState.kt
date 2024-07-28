package app.loococo.mate.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.loococo.domain.model.state.MainUiState
import app.loococo.presentation.login.loginRoute
import app.loococo.presentation.login.navigateToLogin
import app.loococo.presentation.workspace.navigationToWorkspace
import app.loococo.presentation.workspace.workspaceRoute

@Composable
fun rememberMateAppState(
    navController: NavHostController = rememberNavController(),
    uiState: MainUiState,
    authenticatedState: Boolean
): MateAppState {
    return remember(navController, uiState, authenticatedState) {
        MateAppState(
            navController = navController,
            uiState = uiState,
            authenticatedState = authenticatedState
        )
    }
}

@Stable
class MateAppState(
    val navController: NavHostController,
    val uiState: MainUiState,
    val authenticatedState: Boolean
) {

    val startDestination = when (uiState) {
        MainUiState.Workspace -> workspaceRoute
        MainUiState.Login -> loginRoute
    }

    fun handleNavigation() {
        Log.e("---------------", "$authenticatedState")
        if (authenticatedState) {
            navController.navigationToWorkspace()
        } else {
            navController.navigateToLogin()
        }
//        val currentDestination = navController.currentBackStackEntry?.destination?.route
//        val targetDestination = if (authenticatedState) workspaceRoute else loginRoute
//        Log.e("---------------","$currentDestination $targetDestination")
//
//        if (currentDestination != targetDestination) {
//            navController.navigate(targetDestination) {
//                popUpTo(navController.graph.startDestinationId) {
//                    saveState = true
//                }
//                launchSingleTop = true
//                restoreState = true
//            }
//        }
    }
}