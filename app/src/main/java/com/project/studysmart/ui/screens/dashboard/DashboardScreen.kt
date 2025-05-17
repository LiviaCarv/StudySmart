package com.project.studysmart.ui.screens.dashboard

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.ui.components.AddSubjectsCard
import com.project.studysmart.ui.components.CountCard
import com.project.studysmart.ui.components.SectionTitle
import com.project.studysmart.ui.components.SubjectCard
import com.project.studysmart.ui.theme.StudySmartTheme
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient5


@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {

    val subjectList = listOf(
        Subject("Math", 3f, gradient1),
        Subject("Portuguese", 7.0f, gradient5),
        Subject("Geo", 5.0f, gradient1),
        Subject("Physics", 3f, gradient1)
    )

    Scaffold(
        topBar = { DashboardScreenTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
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
                SubjectCardsSection(subjectList = subjectList)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "StudySmart",
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
            headingText = "Subject Count",
            count = "$subjectCount",
            modifier = Modifier.weight(1f)
        )
        CountCard(
            headingText = "Studied Hours",
            count = studiedHours,
            modifier = Modifier.weight(1f)
        )
        CountCard(
            headingText = "Goal Study Hours",
            count = goalHours,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SubjectCardsSection(
    modifier: Modifier = Modifier,
    subjectList: List<Subject>
) {
    Column(modifier) {
        SectionTitle(
            Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            "SUBJECTS",
            Icons.Default.Add,
            {})
        if (subjectList.isEmpty()) {
            AddSubjectsCard()
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(subjectList)
                { subject ->
                    SubjectCard(subject = subject, onClick = {})
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


@Preview
@Composable
private fun DashboardScreenTopBarPrev() {
    StudySmartTheme {
        DashboardScreenTopBar()
    }

}