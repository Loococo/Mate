package app.loococo.domain.model.state


sealed interface MainUiState {
    data object Loading : MainUiState
    data object Success : MainUiState
    data object Failed : MainUiState
}