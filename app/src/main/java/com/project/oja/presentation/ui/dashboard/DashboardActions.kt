package com.project.oja.presentation.ui.dashboard

import com.project.oja.data.model.calendar.CalendarUiState
import java.time.YearMonth

sealed class DashboardActions {
    data object OnAddHourClicked: DashboardActions()
    data object OnSubtractHourClicked: DashboardActions()
    data class OnPreviousMonthClicked(val yearMonth: YearMonth): DashboardActions()
    data class OnNextMonthClicked(val yearMonth: YearMonth): DashboardActions()
    data class OnDateItemClicked(val date: CalendarUiState.Date): DashboardActions()
}
