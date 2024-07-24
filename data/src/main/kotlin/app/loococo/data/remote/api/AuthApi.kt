package app.loococo.data.remote.api

import app.loococo.data.model.network.ResponseData
import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.request.SignUpRequest
import app.loococo.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/aun/p/authn/login")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @POST("/api/aun/p/authn/register")
    suspend fun signUp(@Body data: SignUpRequest): Response<Unit>
}