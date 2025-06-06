package com.project.studysmart.ui.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.sessionList
import com.project.studysmart.taskList
import com.project.studysmart.ui.components.AddSubjectDialog
import com.project.studysmart.ui.components.CountCard
import com.project.studysmart.ui.components.DeleteDialog
import com.project.studysmart.ui.components.StudySessionsSection
import com.project.studysmart.ui.components.TasksSection
import com.project.studysmart.ui.destinations.TaskScreenRouteDestination
import com.project.studysmart.ui.task.TaskScreenNavArgs
import com.project.studysmart.ui.theme.StudySmartTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.roundToInt

data class SubjectScreenNavArgs(
    val subjectId: Int
)

@Destination(navArgsDelegate = SubjectScreenNavArgs::class)
@Composable
fun SubjectScreenRoute(
    navigator: DestinationsNavigator
) {
    SubjectScreen(
        onAddTaskClick = {
            // will be implemented with the viewModel
            val navArg = TaskScreenNavArgs(taskId = null, subjectId = -1)
            navigator.navigate(TaskScreenRouteDestination(navArg))
        },
        onTaskCardClick = { taskId ->
            val navArg = TaskScreenNavArgs(taskId = taskId, subjectId = null)
            navigator.navigate(TaskScreenRouteDestination(navArgs = navArg))
        },
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}

@Composable
private fun SubjectScreen(
    onAddTaskClick: () -> Unit,
    onTaskCardClick: (Int?) -> Unit,
    onBackButtonClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val isFabExpanded by remember {
        derivedStateOf { listState.firstVisibleItemIndex == 0 }
    }

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by rememberSaveable { mutableStateOf("") }
    val studiedHours by rememberSaveable { mutableStateOf("4") }
    var goalHours by rememberSaveable { mutableStateOf("10") }
    val progress by remember {
        derivedStateOf {
            (studiedHours.toFloat() / goalHours.toFloat()).coerceIn(0f, 1f)
        }
    }
    var selectedColor by rememberSaveable { mutableStateOf(emptyList<Color>()) }

    AddSubjectDialog(
        showDialog = isAddSubjectDialogOpen,
        onDismissRequest = {
            isAddSubjectDialogOpen = false
        },
        onConfirmation = {
            isAddSubjectDialogOpen = false
        },
        selectedColor = selectedColor,
        onColorChange = { selectedColor = it },
        subject = subjectName,
        goalStudyHours = goalHours,
        onSubjectNameChange = { subjectName = it },
        onGoalStudyHoursChange = { goalHours = it },
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

    DeleteDialog(
        dialogTitle = "Delete Subject?",
        showDialog = isDeleteSubjectDialogOpen,
        onConfirmation = {
            isDeleteSubjectDialogOpen = false
        },
        onDismissRequest = {
            isDeleteSubjectDialogOpen = false
        },
        bodyText = stringResource(R.string.confirm_delete_subject)
    )

    Scaffold(
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onDeleteIconClick = { isDeleteSubjectDialogOpen = true },
                onEditIconClick = { isAddSubjectDialogOpen = true },
                onArrowBackClick = onBackButtonClick
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddTaskClick,
                icon = { Icon(Icons.Filled.Add, "Add task.") },
                text = { Text(text = "Add Task") },
                expanded = isFabExpanded
            )

        }
    )
    { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            item {
                ProgressIndicator(progress)
            }
            item {
                SubjectOverviewSection(
                    studiedHours = studiedHours,
                    goalHours = goalHours,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                )
            }
            item {
                TasksSection(
                    title = stringResource(R.string.upcoming_tasks_section_title),
                    taskList = taskList,
                    onCheckBoxClick = {},
                    onTaskClick = onTaskCardClick,
                    emptyListText1 = stringResource(R.string.no_tasks),
                    emptyListText2 = stringResource(R.string.add_tasks)
                )
            }
            item {
                TasksSection(
                    modifier = Modifier.padding(top = 20.dp),
                    title = stringResource(R.string.completed_tasks_section_title),
                    taskList = emptyList(),
                    onCheckBoxClick = {},
                    onTaskClick = {},
                    emptyListText1 = stringResource(R.string.no_completed_tasks),
                    emptyListText2 = stringResource(R.string.click_check_box_task_completion)
                )
            }
            item {
                StudySessionsSection(
                    modifier = Modifier.padding(top = 20.dp),
                    sessionsList = sessionList,
                    onDeleteIconClick = {
                        isDeleteSessionDialogOpen = true
                    }
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreenTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onDeleteIconClick: () -> Unit,
    onEditIconClick: () -> Unit,
    onArrowBackClick: () -> Unit,
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                title, maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { onArrowBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back"
                )
            }
        },
        actions = {
            IconButton(onClick = { onDeleteIconClick() }) {
                Icon(
                    imageVector = Icons.Filled.Delete, contentDescription = "Delete"
                )
            }
            IconButton(onClick = { onEditIconClick() }) {
                Icon(
                    imageVector = Icons.Filled.Edit, contentDescription = "Edit"
                )
            }

        },
    )
}

@Composable
private fun SubjectOverviewSection(
    studiedHours: String,
    goalHours: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CountCard(
            headingText = stringResource(R.string.studied_hours),
            count = studiedHours,
            modifier = Modifier.weight(1f)
        )
        CountCard(
            headingText = stringResource(R.string.goal_study_hours),
            count = goalHours,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun ProgressIndicator(progress: Float) {
    val percentageProgress = (progress * 100).roundToInt()
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = "Progress \nIndicator", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .height(100.dp)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { 1f },
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant,
            )
            CircularProgressIndicator(
                progress = { progress },
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
            )
            Text("${percentageProgress}%")

        }
    }
}

@Preview
@Composable
private fun SubjectScreenTopBarPrev() {
    StudySmartTheme {
//        SubjectScreen()
    }
}