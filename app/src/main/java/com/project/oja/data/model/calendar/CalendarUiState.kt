package com.project.oja.data.model.calendar

import java.time.LocalDate
import java.time.YearMonth

data class CalendarUiState(
    val yearMonth: YearMonth,
    val dates: List<Date>
) {
    companion object {
        val Init = CalendarUiState(
            yearMonth = YearMonth.now(),
            dates = emptyList()
        )
    }

    data class Date(
        val dayOfMonth: String,
        val isInDefaultRange: Boolean,
        val isToday: Boolean,
        val localDate: LocalDate
    ) {
        companion object {
            val Empty = Date("", false, false, LocalDate.MIN)
        }
    }
}