package app.loococo.domain.repository

import app.loococo.domain.model.Resource
import app.loococo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String) : Flow<Resource<User>>
}