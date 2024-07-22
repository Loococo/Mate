package app.loococo.domain.usecase

import app.loococo.domain.model.User
import app.loococo.domain.model.network.Resource
import app.loococo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun login(email: String, password: String): Flow<Resource<User>> {
        return authRepository.login(email, password)
    }

    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Flow<Resource<User>> {
        return authRepository.signUp(email, password, firstName, lastName)
    }
}