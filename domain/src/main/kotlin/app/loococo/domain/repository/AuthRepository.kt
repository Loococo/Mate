package app.loococo.domain.repository

import app.loococo.domain.model.User
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Flow<Resource<User>>
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Flow<Resource<User>>
}