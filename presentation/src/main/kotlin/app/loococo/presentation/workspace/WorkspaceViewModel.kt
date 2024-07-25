package app.loococo.presentation.workspace

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.loococo.domain.usecase.WorkspaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class WorkspaceViewModel @Inject constructor(
    private val workspaceUseCase: WorkspaceUseCase
) : ContainerHost<WorkspaceState, WorkspaceEffect>, ViewModel() {

    override val container =
        container<WorkspaceState, WorkspaceEffect>(WorkspaceState())

//    init {
//        viewModelScope.launch {
//            workspaceUseCase.findWorkspaceList()
//        }
//    }

    fun a(){
        viewModelScope.launch {
            Log.e("----------------","1")
            workspaceUseCase.findWorkspaceList().collect{

            }
        }
    }
}