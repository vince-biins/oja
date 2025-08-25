package com.project.oja.data.model.calendar


import com.project.oja.util.getDayOfMonthStartingFromSunday
import java.time.LocalDate
import java.time.YearMonth
class CalendarDataSource {

    fun getDates(
        yearMonth: YearMonth,
        defaultStart: LocalDate,
        defaultEnd: LocalDate
    ): List<CalendarUiState.Date> {
        val today = LocalDate.now()

        return yearMonth.getDayOfMonthStartingFromSunday().map { date ->
            val isCurrentMonth = date.monthValue == yearMonth.monthValue

            val inDefaultRange = !date.isBefore(defaultStart) && !date.isAfter(defaultEnd)

            CalendarUiState.Date(
                dayOfMonth = if (isCurrentMonth) "${date.dayOfMonth}" else "",
                isInDefaultRange = inDefaultRange,
                isToday = date.isEqual(today),
                localDate = date
            )
        }
    }
}
