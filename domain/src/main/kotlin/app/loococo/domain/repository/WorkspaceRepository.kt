package app.loococo.domain.repository

import app.loococo.domain.model.Workspace
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface WorkspaceRepository {

    suspend fun findWorkspaceList(): Flow<Resource<List<Workspace>>>
}