package app.loococo.data.repository

import app.loococo.data.model.request.LoginRequest
import app.loococo.data.remote.manger.AuthDataSource
import app.loococo.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override fun login() {
//        authDataSource.login(LoginRequest("", "")).collect {
//
//        }
    }
}