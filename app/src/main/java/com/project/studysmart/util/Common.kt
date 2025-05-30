package com.project.studysmart.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.ui.graphics.Color
import com.project.studysmart.ui.theme.Green
import com.project.studysmart.ui.theme.Orange
import com.project.studysmart.ui.theme.Red
import java.time.LocalDate
import java.time.ZoneId

enum class Priority(val title: String, val color: Color, val value: Int) {
    LOW(title = "Low", color = Green, value = 0),
    MEDIUM(title = "Medium", color = Orange, value = 1),
    HIGH(title = "High", color = Red, value = 2);

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull { it.value == value } ?: MEDIUM
    }
}

@OptIn(ExperimentalMaterial3Api::class)
object CurrentOrFutureSelectableDates : SelectableDates {

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val currentDateMillis = LocalDate.now()
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()
        return utcTimeMillis >= currentDateMillis

    }

    override fun isSelectableYear(year: Int): Boolean {
        return year >= LocalDate.now().year
    }

}