package app.loococo.data.remote.manger.impl

import app.loococo.data.model.network.suspendResponseResult
import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.response.LoginResponse
import app.loococo.data.remote.api.AuthApi
import app.loococo.data.remote.manger.AuthDataSource
import app.loococo.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthDataSource {
    override suspend fun login(data: LoginRequest): Flow<Resource<LoginResponse>> =
        suspendResponseResult { authApi.login(data) }
}