package app.loococo.data.repository

import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.response.toUser
import app.loococo.data.remote.manger.AuthDataSource
import app.loococo.domain.model.Resource
import app.loococo.domain.model.ResourceException
import app.loococo.domain.model.User
import app.loococo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<User>> = flow {
        authDataSource.login(LoginRequest(email, password)).collect {
            when (it) {
                is Resource.Success -> {
                    emit(Resource.Success(it.data.toUser()))
                }

                is Resource.Error -> {
                    when (val exception = it.exception) {
                        is ResourceException.HttpException -> {
                            when (exception.code) {
                                400 -> {
                                }
                            }
                        }

                        else -> emit(it)
                    }
                }
            }
        }
    }
}