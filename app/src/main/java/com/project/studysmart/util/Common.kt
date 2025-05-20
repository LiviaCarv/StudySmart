package com.project.studysmart.util

import androidx.compose.ui.graphics.Color
import com.project.studysmart.ui.theme.Green
import com.project.studysmart.ui.theme.Orange
import com.project.studysmart.ui.theme.Red

enum class Priority(val title: String, val color: Color, val value: Int) {
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HIGH(title = "High", color = Red, value = 2);

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull { it.value == value } ?: MEDIUM
    }
}