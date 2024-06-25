package app.loococo.domain.usecase

import app.loococo.domain.model.Resource
import app.loococo.domain.model.User
import app.loococo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun login(email: String, password: String) : Flow<Resource<User>> {
        return authRepository.login(email, password)
    }
}