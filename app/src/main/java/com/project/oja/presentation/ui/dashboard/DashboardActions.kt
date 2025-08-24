package com.project.oja.presentation.ui.dashboard

import com.project.oja.data.model.calendar.CalendarUiState
import java.time.YearMonth

sealed class DashboardActions {
    data class OnAddHourClicked(val hour: Float): DashboardActions()
    data class OnSubtractHourClicked(val hour: Float): DashboardActions()
    data class OnPreviousMonthClicked(val yearMonth: YearMonth): DashboardActions()
    data class OnNextMonthClicked(val yearMonth: YearMonth): DashboardActions()
    data class OnDateItemClicked(val date: CalendarUiState.Date): DashboardActions()
}
