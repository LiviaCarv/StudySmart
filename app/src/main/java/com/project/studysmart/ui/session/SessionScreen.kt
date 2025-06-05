package com.project.studysmart.ui.session

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.ui.components.DeleteDialog
import com.project.studysmart.ui.components.RelatedToSubjectSession
import com.project.studysmart.ui.components.StudySessionsSection
import com.project.studysmart.ui.components.SubjectListBottomSheet
import com.project.studysmart.ui.theme.StudySmartTheme
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient5
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun SessionScreenRoute(
    navigator: DestinationsNavigator
) {
    SessionScreen(
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SessionScreen(
    onBackButtonClick: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }

    SubjectListBottomSheet(
        sheetState = bottomSheetState,
        onDismissClick = { showBottomSheet = false },
        subjectList = listOf(
            Subject(1, "Math", 3f, gradient1),
            Subject(2, "Portuguese", 7.0f, gradient5),
            Subject(3, "Geo", 5.0f, gradient1),
            Subject(4, "Physics", 3f, gradient1)
        ),
        showBottomSheet = showBottomSheet,
        onSubjectClicked = {
            scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                if (!bottomSheetState.isVisible) {
                    showBottomSheet = false
                }
            }
        }
    )

    DeleteDialog(
        showDialog = isDeleteSessionDialogOpen,
        onConfirmation = {
            isDeleteSessionDialogOpen = false
        },
        onDismissRequest = {
            isDeleteSessionDialogOpen = false
        },
        bodyText = stringResource(R.string.confirm_delete_study_session)
    )

    Scaffold(
        topBar = {
            SessionScreenTopBar(onArrowBackClick = onBackButtonClick)
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                TimerSection(
                    modifier = Modifier
                )
            }
            item {
                RelatedToSubjectSession(
                    modifier = Modifier.padding(12.dp),
                    relatedToSubject = "English",
                    selectSubjectButtonClick = { showBottomSheet = true }
                )
            }
            item {
                TimerButtonsSection(
                    onCancelClick = {},
                    onStartClick = {},
                    onFinishClick = {}
                )
            }

            item {
                StudySessionsSection(
                    title = "Study sessions history",
                    sessionsList = emptyList(),
                    onDeleteIconClick = { isDeleteSessionDialogOpen = true }
                )
            }
        }

    }

}

@Composable
fun TimerSection(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,

        ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .border(
                    width = 5.dp,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceVariant
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Timer",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 45.sp)
            )
        }
    }
}

@Composable
fun TimerButtonsSection(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onStartClick: () -> Unit,
    onFinishClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onCancelClick,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(text = "Cancel")
        }
        Button(
            onClick = onStartClick,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)

        ) {
            Text(text = "Start")
        }
        Button(
            onClick = onFinishClick,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(text = "Finish")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreenTopBar(
    onArrowBackClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = "Study Sessions",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = onArrowBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
        },


        )
}


@Preview
@Composable
private fun SessionScreenPrev() {
    StudySmartTheme {
        SessionScreen({})
    }
}