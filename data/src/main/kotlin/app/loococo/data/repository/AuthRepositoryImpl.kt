package app.loococo.data.repository

import app.loococo.data.ext.isValidEmail
import app.loococo.data.ext.isValidName
import app.loococo.data.ext.isValidPassword
import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.request.SignUpRequest
import app.loococo.data.model.response.toUser
import app.loococo.data.remote.manger.AuthDataSource
import app.loococo.domain.model.User
import app.loococo.domain.model.network.Resource
import app.loococo.domain.model.network.ResourceException
import app.loococo.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    private fun validateCredentials(email: String, password: String): Resource.Message? {
        return when {
            email.isBlank() || password.isBlank() -> Resource.Message("Please enter your info")
            !email.isValidEmail() -> Resource.Message("Invalid email address")
            !password.isValidPassword() -> Resource.Message("Password must be at least 8 characters")
            else -> null
        }
    }

    private fun validateNames(firstName: String, lastName: String): Resource.Message? {
        return when {
            firstName.isBlank() || lastName.isBlank() -> Resource.Message("Please enter your first and last name")
            !firstName.isValidName() || !lastName.isValidName() -> Resource.Message("Name must be at least 2 characters")
            else -> null
        }
    }

    private fun validateSignUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Resource.Message? {
        return validateCredentials(email, password) ?: validateNames(firstName, lastName)
    }

    override suspend fun login(email: String, password: String): Flow<Resource<User>> = flow {
        val validationError = validateCredentials(email, password)
        if (validationError != null) {
            emit(validationError)
            return@flow
        }

        authDataSource.login(LoginRequest(email, password)).collect { result ->
            emit(
                when (result) {
                    is Resource.Success -> Resource.Success(result.data.toUser())
                    is Resource.Error -> {
                        when (val exception = result.exception) {
                            is ResourceException.HttpException -> {
                                if (exception.code == "api.authn.invalid_credential") {
                                    Resource.Message("Invalid credential")
                                } else {
                                    result
                                }
                            }

                            else -> result
                        }
                    }

                    is Resource.Message -> result
                }
            )
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Flow<Resource<User>> = flow {
        val validationError = validateSignUp(email, password, firstName, lastName)
        if (validationError != null) {
            emit(validationError)
            return@flow
        }

        authDataSource.signUp(SignUpRequest(email, password, firstName, lastName))
            .collect { result ->
                emit(
                    when (result) {
                        is Resource.Success -> Resource.Success(result.data.toUser())
                        is Resource.Error -> result
                        is Resource.Message -> result
                    }
                )
            }
    }
}
