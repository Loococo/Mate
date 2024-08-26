package app.loococo.mate.di.network

import app.loococo.data.model.request.TokenRequest
import app.loococo.data.model.response.toToken
import app.loococo.data.remote.api.AuthApi
import app.loococo.domain.repository.PreferencesRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val authApi: AuthApi,
    private val authenticationManager: AuthenticationManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = preferencesRepository.getToken()

        if (token == null) {
            authenticationManager.logout(true)
            return null
        }

        val newToken = try {
            val refreshTokenResponse =
                authApi.refresh(TokenRequest(token.accessToken, token.refreshToken)).execute()
            if (refreshTokenResponse.isSuccessful) {
                refreshTokenResponse.body()
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }

        if (newToken == null) {
            authenticationManager.logout(true)
        } else {
            authenticationManager.logout(false)
            preferencesRepository.saveToken(newToken.toToken())
        }

        return response.request.newBuilder()
            .removeHeader("Authorization")
            .addHeader("Authorization", "Bearer ${newToken?.accessToken ?: ""}")
            .build()
    }
}

