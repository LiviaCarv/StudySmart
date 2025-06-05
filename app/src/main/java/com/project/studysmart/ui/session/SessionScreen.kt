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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.studysmart.ui.components.RelatedToSubjectSession
import com.project.studysmart.ui.components.StudySessionsSection
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun SessionScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            SessionScreenTopBar(onArrowBackClick = {})
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = modifier
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
                    selectSubjectButtonClick = {}
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
                    onDeleteIconClick = {}
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
        SessionScreen()
    }
}