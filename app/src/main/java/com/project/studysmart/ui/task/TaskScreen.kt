package com.project.studysmart.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.ui.components.DatePicker
import com.project.studysmart.ui.components.DeleteDialog
import com.project.studysmart.ui.components.LongButton
import com.project.studysmart.ui.components.RelatedToSubjectSession
import com.project.studysmart.ui.components.SubjectListBottomSheet
import com.project.studysmart.ui.components.TaskCheckBox
import com.project.studysmart.ui.theme.Red
import com.project.studysmart.ui.theme.StudySmartTheme
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient5
import com.project.studysmart.util.CurrentOrFutureSelectableDates
import com.project.studysmart.util.Priority
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


data class TaskScreenNavArgs(
    val subjectId: Int?,
    val taskId: Int?
)

@Destination(navArgsDelegate = TaskScreenNavArgs::class)
@Composable
fun TaskScreenRoute(
    navigator: DestinationsNavigator
) {
    TaskScreen(
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreen(
    onBackButtonClick: () -> Unit
) {
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    var isDeleteTaskDialogOpen by rememberSaveable { mutableStateOf(false) }

    var titleError by remember { mutableStateOf<String?>(null) }
    titleError = when {
        title.isBlank() -> "Please enter title."
        title.length < 3 -> "Title is too short."
        title.length > 30 -> "Title is too long."
        else -> null
    }

    val scope = rememberCoroutineScope()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = CurrentOrFutureSelectableDates
    )

    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }

    val millis = datePickerState.selectedDateMillis
    val localDate = millis?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneOffset.UTC) // sempre UTC
            .toLocalDate()
    }
    val formattedDate =
        localDate?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) ?: "Select Date"

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

    DatePicker(
        datePickerState = datePickerState,
        showDatePicker = showDatePicker,
        onDateSelected = { selectedDateMillis = it },
        onDismiss = { showDatePicker = false },
    )

    DeleteDialog(
        dialogTitle = "Delete Task?",
        showDialog = isDeleteTaskDialogOpen,
        onConfirmation = {
            isDeleteTaskDialogOpen = false
        },
        onDismissRequest = {
            isDeleteTaskDialogOpen = false
        },
        bodyText = stringResource(R.string.confirm_delete_task)
    )

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                onDeleteIconClick = { isDeleteTaskDialogOpen = true },
                onCheckBoxClick = {},
                onArrowBackClick = onBackButtonClick,
                isTaskExist = true,
                isComplete = false,
                checkBoxBorderColor = Red,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
                .verticalScroll(state = rememberScrollState())
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                singleLine = true,
                supportingText = { Text(text = titleError.orEmpty()) },
                isError = titleError != null && title.isNotBlank()
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
            )
            Text(
                text = "Due date",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.Start)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(
                    onClick = { showDatePicker = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Choose due date"
                    )
                }
            }
            Text(
                text = "Priority",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.align(Alignment.Start)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Priority.entries.forEach { priority ->
                    PriorityButton(
                        modifier = Modifier.weight(1f),
                        priority = priority,
                        onClick = {}
                    )
                }
            }
            RelatedToSubjectSession(
                relatedToSubject = "English",
                selectSubjectButtonClick = { showBottomSheet = true }
            )
            LongButton(
                text = "Save",
                onClick = {},
                isEnabled = title.isNotBlank() && titleError.isNullOrEmpty()
            )
        }

    }
}

@Composable
fun PriorityButton(
    modifier: Modifier = Modifier,
    priority: Priority,
    onClick: () -> Unit,
    borderColor: Color = Color.Transparent
) {
    Box(
        modifier = modifier
            .background(priority.color)
            .clickable { onClick() }
            .padding(5.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(5.dp), color = borderColor)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = priority.title, color = Color.White, style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreenTopBar(
    isTaskExist: Boolean,
    isComplete: Boolean,
    checkBoxBorderColor: Color,
    onDeleteIconClick: () -> Unit,
    onCheckBoxClick: () -> Unit,
    onArrowBackClick: () -> Unit,
) {

    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = "Task",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
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
            if (isTaskExist) {
                TaskCheckBox(
                    isComplete = isComplete,
                    borderColor = checkBoxBorderColor,
                    onCheckBoxClick = onCheckBoxClick
                )
                IconButton(onClick = { onDeleteIconClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Delete, contentDescription = "Delete"
                    )
                }

            }
        },
    )
}

@Preview
@Composable
private fun TaskScreenPrev() {
    StudySmartTheme {
        TaskScreen({})
    }
}