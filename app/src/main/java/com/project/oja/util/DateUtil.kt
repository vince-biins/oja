package com.project.oja.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale

object DateUtil {

    val daysOfWeek: Array<String>
        get() {
            // Reorder so that Sunday is first
            val orderedDays = listOf(
                DayOfWeek.SUNDAY,
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY
            )

            return orderedDays.map { day ->
                day.getDisplayName(TextStyle.SHORT, Locale.getDefault())
            }.toTypedArray()
        }
}

fun YearMonth.getDayOfMonthStartingFromSunday(): List<LocalDate> {
    val firstDayOfMonth = LocalDate.of(year, month, 1)

    val firstSundayOfMonth = firstDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))

    val firstDayOfNextMonth = firstDayOfMonth.plusMonths(1)

    return generateSequence(firstSundayOfMonth) { it.plusDays(1) }
        .takeWhile { it.isBefore(firstDayOfNextMonth) }
        .toList()
}
fun YearMonth.getDisplayName(): String {
    return "${month.getDisplayName(TextStyle.FULL, Locale.getDefault())} $year"
}