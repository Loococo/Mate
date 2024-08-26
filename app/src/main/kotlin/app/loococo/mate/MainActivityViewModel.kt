package app.loococo.mate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.model.state.MainUiState
import app.loococo.domain.usecase.PreferencesUseCase
import app.loococo.mate.di.network.AuthenticationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    useCase: PreferencesUseCase,
    authenticationManager: AuthenticationManager
) : ViewModel() {

    val uiState: StateFlow<MainUiState> = useCase.getUserState()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MainUiState.Splash)

    val logoutState: StateFlow<Boolean> = authenticationManager.logoutState

}