package com.project.studysmart.ui.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.project.studysmart.ui.components.CountCard
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
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
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    subjectCount = 5,
                    goalHours = "5",
                    studiedHours = "5")
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
fun CountCardsSection(
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

@Preview(showBackground = true)
@Composable
private fun CountCardsSectionPrev() {
    StudySmartTheme {
        CountCardsSection(3,"10","12")
    }
}


@Preview
@Composable
private fun DashboardScreenTopBarPrev() {
    StudySmartTheme {
        DashboardScreenTopBar()
    }

}