package app.loococo.mate.di.network

import app.loococo.domain.usecase.PreferencesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationManager @Inject constructor(
    private val preferencesUseCase: PreferencesUseCase
) {
    private val _logoutState = MutableStateFlow(preferencesUseCase.logoutState())
    val logoutState: StateFlow<Boolean> = _logoutState

    fun logout(value: Boolean) {
        _logoutState.value = value

        if (value) {
            preferencesUseCase.logout()
        }
    }
}