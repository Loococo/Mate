package app.loococo.mate.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.loococo.domain.model.state.MainUiState
import app.loococo.presentation.login.loginRoute
import app.loococo.presentation.workspace.workspaceRoute

@Composable
fun rememberMateAppState(
    navController: NavHostController = rememberNavController(),
    uiState: MainUiState
): MateAppState {
    return remember(navController, uiState) {
        MateAppState(
            navController = navController,
            uiState = uiState
        )
    }
}

@Stable
class MateAppState(
    val navController: NavHostController,
    val uiState: MainUiState
) {

    val startDestination = when (uiState) {
        MainUiState.Workspace -> workspaceRoute
        MainUiState.Login -> loginRoute
    }
}