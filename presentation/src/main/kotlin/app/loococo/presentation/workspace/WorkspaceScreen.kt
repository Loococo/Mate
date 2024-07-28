package app.loococo.presentation.workspace

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.presentation.component.MateLabelText
import app.loococo.presentation.utils.MateIcons

@Composable
internal fun WorkspaceRoute(showToast: (String) -> Unit) {
    WorkspaceScreen(showToast)
}

@Composable
fun WorkspaceScreen(showToast: (String) -> Unit) {
    val viewModel: WorkspaceViewModel = hiltViewModel()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()
    val sideEffectFlow = viewModel.container.sideEffectFlow

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                WorkspaceEffect.NavigationHome -> {

                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
//        LazyColumn {
//            items(state.workspaceList) { workspace ->
//                WorkspaceItem()
//            }
//        }
        Image(imageVector = MateIcons.Plus, contentDescription = "plus")
        WorkspaceCreate()
    }
}

@Composable
fun WorkspaceItem() {
    Row(modifier = Modifier.fillMaxWidth()) {

    }
}

@Composable
fun WorkspaceCreate() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(imageVector = MateIcons.Plus, contentDescription = "plus")
        MateLabelText(text = "create")
    }
}