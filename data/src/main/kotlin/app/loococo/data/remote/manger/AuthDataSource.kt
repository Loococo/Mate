package app.loococo.data.remote.manger

import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.request.SignUpRequest
import app.loococo.data.model.response.LoginResponse
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun login(data: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun signUp(data: SignUpRequest): Flow<Resource<Unit>>
}