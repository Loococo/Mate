package app.loococo.presentation.workspace

import androidx.compose.ui.text.input.TextFieldValue
import app.loococo.domain.model.Workspace

data class WorkspaceState(
    val workspaceList: List<Workspace> = emptyList(),
    val isLoading: Boolean = false,
    val showPopup: Boolean = false,
    val workspaceName: TextFieldValue = TextFieldValue(""),
    val workspaceSlug: TextFieldValue = TextFieldValue("")
)

sealed class WorkspaceEffect {
    data object NavigationHome : WorkspaceEffect()
    data class ShowToast(val message: String) : WorkspaceEffect()
}

sealed class WorkspaceIntent {
    data object WorkspaceCreateClicked : WorkspaceIntent()
    data class WorkspaceNameChanged(val name: TextFieldValue) : WorkspaceIntent()
    data class WorkspaceSlugChanged(val slug: TextFieldValue) : WorkspaceIntent()
    data object WorkspaceCreateConfirmed : WorkspaceIntent()
}