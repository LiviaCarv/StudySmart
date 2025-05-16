package com.project.studysmart.domain.model

import android.provider.CalendarContract.Colors
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient2
import com.project.studysmart.ui.theme.gradient3
import com.project.studysmart.ui.theme.gradient4
import com.project.studysmart.ui.theme.gradient5

data class Subject(
    val name: String,
    val goalHours: Float,
    val colors: List<Colors>
) {
    companion object {
        val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}
