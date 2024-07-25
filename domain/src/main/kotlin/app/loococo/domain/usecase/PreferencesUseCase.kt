package app.loococo.domain.usecase

import app.loococo.domain.model.state.MainUiState
import app.loococo.domain.repository.PreferencesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferencesUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    fun getUserState(): Flow<MainUiState> = flow {
        emit(MainUiState.Loading)
        delay(1000)
        val user = preferencesRepository.getUser()
        if (user != null) {
            emit(MainUiState.Success)
        } else {
            emit(MainUiState.Failed)
        }
    }

}