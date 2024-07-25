package app.loococo.data.repository

import android.util.Log
import app.loococo.data.remote.manger.WorkspaceDataSource
import app.loococo.domain.model.network.Resource
import app.loococo.domain.repository.WorkspaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WorkspaceRepositoryImpl @Inject constructor(
    private val workspaceDataSource: WorkspaceDataSource
) : WorkspaceRepository {

    override suspend fun findWorkspaceList(): Flow<Resource<Unit>> = flow {
        Log.e("----------------","2")
        workspaceDataSource.findWorkspaceList().collect {
            Log.e("----------------","$it")
            emit(it)
        }
    }
}