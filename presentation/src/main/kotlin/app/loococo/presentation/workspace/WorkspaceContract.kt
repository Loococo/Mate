package app.loococo.presentation.workspace

import app.loococo.domain.model.Workspace

data class WorkspaceState(
    val workspaceList: List<Workspace> = emptyList(),
    val isLoading: Boolean = false
)

sealed class WorkspaceEffect {
    data object NavigationHome : WorkspaceEffect()
}

sealed class WorkspaceIntent {
}