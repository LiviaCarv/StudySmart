package com.project.studysmart.ui.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Task
import com.project.studysmart.ui.components.CountCard
import com.project.studysmart.ui.components.UpcomingTasksSection
import com.project.studysmart.ui.theme.StudySmartTheme
import kotlin.math.roundToInt

@Composable
fun SubjectScreen(modifier: Modifier = Modifier) {
    val taskList = listOf(
        Task(1, 2, "Prepare notes", "need to study", 4L, 0, "", true),
        Task(1, 2, "Watch next lesson", "need to study", 4L, 1, "", false),
        Task(1, 2, "Study 2 hrs", "need to study", 4L, 2, "", false)
    )

    val studiedHours = "2.3"
    val goalHours = "10.0"
    val progress = studiedHours.toFloat() / goalHours.toFloat()

    Scaffold(
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onDeleteIconClick = {},
                onEditIconClick = {},
                onArrowBackClick = {}
            )
        }
    )
    { paddingValues ->
        LazyColumn(
            modifier = modifier
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
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                )
            }
            item {
                UpcomingTasksSection(taskList = taskList, onCheckBoxClick = {}, onTaskClick = {})
            }
            item {
                
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
            modifier = Modifier.height(75.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { 1f },
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.surfaceVariant
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
        SubjectScreen()
    }
}