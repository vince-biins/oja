package com.project.oja.presentation.ui.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.project.oja.data.model.HourRecord
import com.project.oja.presentation.components.calendar.CalendarViewModel
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardState

@Composable
fun DashboardRoot(
    modifier: Modifier = Modifier,
    viewModel: CalendarViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val dashboardState = DashboardState(
        hourRecord = HourRecord(
            totalHours = 320f,
            hoursTaken = 24f
        )
    )
    val uiState by viewModel.uiState.collectAsState()

    val handleDashboardActions: (DashboardActions) -> Unit = {
        when (it) {
            DashboardActions.OnAddHourClicked,
            DashboardActions.OnSubtractHourClicked -> handleHourActions(it)

            is DashboardActions.OnDateItemClicked,
            is DashboardActions.OnPreviousMonthClicked,
            is DashboardActions.OnNextMonthClicked -> handleCalendarActions(it, viewModel)
        }
    }
    DashboardScreen(
        modifier = modifier,
        dashboardState = dashboardState,
        calendarUiState = uiState,
        actions = handleDashboardActions,
    )

}

fun handleHourActions(action: DashboardActions, ) {
    when (action) {
        DashboardActions.OnAddHourClicked -> {}
        DashboardActions.OnSubtractHourClicked -> {}
        else -> Unit
    }
}

fun handleCalendarActions(action: DashboardActions, viewModel: CalendarViewModel) {
    when (action) {
        is DashboardActions.OnNextMonthClicked -> {viewModel.toNextMonth(action.yearMonth)}
        is DashboardActions.OnPreviousMonthClicked -> {viewModel.toPreviousMonth(action.yearMonth)}
        is DashboardActions.OnDateItemClicked -> {}
        else -> Unit
    }
}