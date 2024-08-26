package app.loococo.presentation.workspace

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.domain.model.Workspace
import app.loococo.presentation.component.CircularProgress
import app.loococo.presentation.component.HeightSpacer
import app.loococo.presentation.component.MateLabelText
import app.loococo.presentation.component.MateLogoText
import app.loococo.presentation.component.MateWorkspaceCreatePopup
import app.loococo.presentation.component.WidthSpacer
import app.loococo.presentation.theme.Blue1
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

                is WorkspaceEffect.ShowToast -> showToast(sideEffect.message)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        WorkspaceHeader()
        WorkspaceList(
            items = state.workspaceList,
            onCreate = {
                viewModel.handleIntent(WorkspaceIntent.WorkspaceCreateClicked)
            }
        )
    }

    if (state.isLoading) {
        CircularProgress()
    }

    if (state.showPopup) {
        MateWorkspaceCreatePopup(
            onDismissRequest = {
                viewModel.handleIntent(WorkspaceIntent.WorkspaceCreateClicked)
            },
            name = state.workspaceName,
            slug = state.workspaceSlug,
            changeName = {
                viewModel.handleIntent(WorkspaceIntent.WorkspaceNameChanged(it))
            },
            changeSlug = {
                viewModel.handleIntent(WorkspaceIntent.WorkspaceSlugChanged(it))
            },
            create = {
                viewModel.handleIntent(WorkspaceIntent.WorkspaceCreateConfirmed)
            }
        )
    }
}

@Composable
fun WorkspaceHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MateLogoText(text = "Workspace", fontWeight = FontWeight.Light)
    }
}

@Composable
fun WorkspaceList(items: List<Workspace>, onCreate: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        LazyColumn {
            items(items) { workspace ->
                WorkspaceItem(item = workspace)
            }
            item {
                WorkspaceCreate(onCreate)
            }
        }
    }
}

@Composable
fun WorkspaceItem(item: Workspace) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Blue1, RoundedCornerShape(10.dp))
            .padding(20.dp, 10.dp)
    ) { MateLabelText(text = item.name) }
    HeightSpacer(height = 5)
}

@Composable
fun WorkspaceCreate(onCreate: () -> Unit) {
    Box(modifier = Modifier.clickable { onCreate.invoke() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Blue1, RoundedCornerShape(10.dp))
                .padding(20.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                imageVector = MateIcons.Plus,
                contentDescription = "plus"
            )
            WidthSpacer(width = 5)
            MateLabelText(text = "create")
        }
    }
}