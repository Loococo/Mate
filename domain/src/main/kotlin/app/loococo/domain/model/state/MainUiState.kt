package app.loococo.domain.model.state


sealed interface MainUiState {
    data object Workspace : MainUiState
    data object Login : MainUiState
}

sealed interface SplashState{
    data object Loading:SplashState
    data object Success:SplashState
}