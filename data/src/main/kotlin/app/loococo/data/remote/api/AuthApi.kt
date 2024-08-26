package app.loococo.data.remote.api

import app.loococo.data.model.request.LoginRequest
import app.loococo.data.model.request.SignUpRequest
import app.loococo.data.model.request.TokenRequest
import app.loococo.data.model.response.LoginResponse
import app.loococo.data.model.response.LoginTokenData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/usr/p/users/login")
    suspend fun login(@Body data: LoginRequest): Response<LoginResponse>

    @POST("/api/usr/p/users/signup")
    suspend fun signUp(@Body data: SignUpRequest): Response<Unit>

    @POST("/api/usr/p/users/refresh")
    fun refresh(@Body data: TokenRequest): Call<LoginTokenData>
}