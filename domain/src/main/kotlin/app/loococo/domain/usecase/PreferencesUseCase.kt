package app.loococo.domain.usecase

import app.loococo.domain.model.state.MainUiState
import app.loococo.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferencesUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {

    fun getUserState(): Flow<MainUiState> = flow {
        val user = preferencesRepository.getUser()
        emit(if (user != null) MainUiState.Workspace else MainUiState.Login)
    }

    fun logout() {
        preferencesRepository.removeUser()
    }

    fun logoutState(): Boolean {
        return preferencesRepository.getUser() == null
    }
}