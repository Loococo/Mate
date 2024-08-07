package app.loococo.presentation.workspace

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val workspaceRoute = "workspace_route"

fun NavGraphBuilder.workspaceScreen(showToast: (String) -> Unit){
    composable(workspaceRoute) {
        WorkspaceRoute(showToast)
    }
}