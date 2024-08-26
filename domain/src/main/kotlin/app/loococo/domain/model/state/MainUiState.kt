package app.loococo.domain.model.state

sealed interface MainUiState {
    data object Splash : MainUiState
    data object Login : MainUiState
    data object Workspace : MainUiState
}