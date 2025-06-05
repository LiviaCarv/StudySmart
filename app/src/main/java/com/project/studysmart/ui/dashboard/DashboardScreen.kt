package com.project.studysmart.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Session
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.domain.model.Task
import com.project.studysmart.ui.components.AddSubjectDialog
import com.project.studysmart.ui.components.CountCard
import com.project.studysmart.ui.components.DeleteDialog
import com.project.studysmart.ui.components.EmptyCardsContent
import com.project.studysmart.ui.components.LongButton
import com.project.studysmart.ui.components.SectionTitle
import com.project.studysmart.ui.components.StudySessionsSection
import com.project.studysmart.ui.components.SubjectCard
import com.project.studysmart.ui.components.TasksSection
import com.project.studysmart.ui.destinations.SessionScreenRouteDestination
import com.project.studysmart.ui.destinations.SubjectScreenRouteDestination
import com.project.studysmart.ui.destinations.TaskScreenRouteDestination
import com.project.studysmart.ui.subject.SubjectScreenNavArgs
import com.project.studysmart.ui.task.TaskScreenNavArgs
import com.project.studysmart.ui.theme.StudySmartTheme
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient5
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DashboardScreenRoute(
    navigator: DestinationsNavigator
) {
    DashboardScreen(
        onSubjectCardClick = { subjectId ->
            subjectId?.let {
                val navArg = SubjectScreenNavArgs(subjectId = subjectId)
                navigator.navigate(SubjectScreenRouteDestination(navArgs = navArg))
            }
        },
        onTaskCardClick = { taskId ->
            val navArg = TaskScreenNavArgs(taskId = taskId, subjectId = null)
            navigator.navigate(TaskScreenRouteDestination(navArgs = navArg))
        },
        onStartStudySessionClick = {
            navigator.navigate(SessionScreenRouteDestination)
        }
    )
}

@Composable
private fun DashboardScreen(
    onSubjectCardClick: (Int?) -> Unit,
    onTaskCardClick: (Int?) -> Unit,
    onStartStudySessionClick: () -> Unit,
) {

    val subjectList = listOf(
        Subject(1, "Math", 3f, gradient1),
        Subject(2, "Portuguese", 7.0f, gradient5),
        Subject(3, "Geo", 5.0f, gradient1),
        Subject(4, "Physics", 3f, gradient1)
    )

    val taskList = listOf(
        Task(1, 2, "Prepare notes", "need to study", 4L, 0, "", true),
        Task(1, 2, "Watch next lesson", "need to study", 4L, 1, "", false),
        Task(1, 2, "Study 2 hrs", "need to study", 4L, 2, "", false)
    )

    val sessionList = listOf(
        Session(sessionSubjectId = 1, relatedToSubject = "English", 1234L, 2L, 0),
        Session(sessionSubjectId = 2, relatedToSubject = "Portuguese", 1234L, 2L, 1),
        Session(sessionSubjectId = 3, relatedToSubject = "Maths", 1234L, 2L, 2),
        Session(sessionSubjectId = 4, relatedToSubject = "Physics", 1234L, 2L, 3),
    )

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by rememberSaveable { mutableStateOf("") }
    var goalHours by rememberSaveable { mutableStateOf("") }
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

    Scaffold(
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = 5,
                    goalHours = "5",
                    studiedHours = "5"
                )
            }
            item {
                SubjectCardsSection(
                    subjectList = subjectList,
                    onAddIconClick = { isAddSubjectDialogOpen = true },
                    onSubjectCardClick = onSubjectCardClick
                )
            }
            item {
                LongButton(
                    text = stringResource(R.string.start_study_session),
                    onClick = onStartStudySessionClick,
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 48.dp)
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
                StudySessionsSection(
                    modifier = Modifier.padding(vertical = 20.dp),
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
fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
private fun CountCardsSection(
    subjectCount: Int,
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
            headingText = stringResource(R.string.subject_count),
            count = "$subjectCount",
            modifier = Modifier.weight(1f)
        )
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
private fun SubjectCardsSection(
    modifier: Modifier = Modifier,
    subjectList: List<Subject>,
    onAddIconClick: () -> Unit,
    onSubjectCardClick: (Int?) -> Unit
) {
    Column(modifier) {
        SectionTitle(
            Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            stringResource(R.string.subject_section_title),
            Icons.Default.Add,
            onAddIconClick
        )
        if (subjectList.isEmpty()) {
            EmptyCardsContent(
                imageRes = R.drawable.img_books,
                emptyListText1 = stringResource(R.string.no_subjects),
                emptyListText2 = stringResource(R.string.add_subject)
            )
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(subjectList)
                { subject ->
                    SubjectCard(
                        subject = subject,
                        onClick = { onSubjectCardClick(subject.subjectId) })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CountCardsSectionPrev() {
    StudySmartTheme {
        CountCardsSection(3, "10", "12")
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPrev() {
    StudySmartTheme {
//        DashboardScreen()
    }
}


@Preview
@Composable
private fun DashboardScreenTopBarPrev() {
    StudySmartTheme {
        DashboardScreenTopBar()
    }

}