package app.loococo.presentation.workspace

data class WorkspaceState(
    val workspaceList: List<String> = emptyList()
)

sealed class WorkspaceEffect {
}

sealed class WorkspaceIntent {
}