package app.loococo.data.remote.manger

import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.response.LoginResponse
import app.loococo.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    suspend fun login(data: LoginRequest): Flow<Resource<LoginResponse>>
}