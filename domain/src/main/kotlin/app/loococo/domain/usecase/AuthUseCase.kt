package app.loococo.domain.usecase

import app.loococo.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun login()  = authRepository.login()
}