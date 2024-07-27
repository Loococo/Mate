package app.loococo.data.remote.manger

import app.loococo.data.model.response.WorkspaceResponse
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface WorkspaceDataSource {
    suspend fun findWorkspaceList(): Flow<Resource<List<WorkspaceResponse>>>
}