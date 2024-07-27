package app.loococo.domain.usecase

import app.loococo.domain.model.Workspace
import app.loococo.domain.model.network.Resource
import app.loococo.domain.repository.WorkspaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WorkspaceUseCase @Inject constructor(
    private val workspaceRepository: WorkspaceRepository
) {
    suspend fun findWorkspaceList(): Flow<Resource<List<Workspace>>> {
        return workspaceRepository.findWorkspaceList()
    }
}