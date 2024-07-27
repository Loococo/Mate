package app.loococo.domain.usecase

import app.loococo.domain.model.state.MainUiState
import app.loococo.domain.model.state.SplashState
import app.loococo.domain.repository.PreferencesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferencesUseCase @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    fun getSplashState(): Flow<SplashState> = flow {
        emit(SplashState.Loading)
        delay(2000)
        emit(SplashState.Success)
    }

    fun getUserState(): Flow<MainUiState> = flow {
        val user = preferencesRepository.getUser()
        if (user != null) {
            emit(MainUiState.Workspace)
        } else {
            emit(MainUiState.Login)
        }
    }
}