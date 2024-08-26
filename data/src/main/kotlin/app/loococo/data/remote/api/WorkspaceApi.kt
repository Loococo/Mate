package app.loococo.data.remote.api

import app.loococo.data.model.request.WorkspaceRequest
import app.loococo.data.model.response.WorkspaceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WorkspaceApi {

    @GET("/api/workspaces")
    suspend fun findWorkspaceList(): Response<List<WorkspaceResponse>>

    @POST("/api/workspaces")
    suspend fun create(@Body request: WorkspaceRequest): Response<Unit>

}