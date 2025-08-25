package com.project.oja.presentation.ui.dashboard

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.project.oja.presentation.components.calendar.CalendarViewModel
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardEffect
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardEvent
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardRoot(
    modifier: Modifier = Modifier,
    calendarViewModel: CalendarViewModel = koinViewModel(),
    dashboardViewModel: DashboardViewModel = koinViewModel()
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiEvent by dashboardViewModel.uiEffect.collectAsState(initial = null)
    LaunchedEffect(uiEvent) {
        when(uiEvent) {
            is DashboardEffect.ErrorMessage -> {
                errorMessage = (uiEvent as DashboardEffect.ErrorMessage).message
            }
            null -> Unit
        }
    }

    val calendarState by calendarViewModel.uiState.collectAsState()
    val dashboardState by dashboardViewModel.uiState.collectAsState()
    val handleDashboardActions: (DashboardActions) -> Unit = { action ->
        when (action) {
            is DashboardActions.OnAddHourClicked ->
                dashboardViewModel.onEvent(DashboardEvent.IncrementHours(action.hour))
            is DashboardActions.OnSubtractHourClicked ->
                dashboardViewModel.onEvent(DashboardEvent.DecrementHours(action.hour))
            is DashboardActions.OnDateItemClicked -> {}
            is DashboardActions.OnPreviousMonthClicked ->
                calendarViewModel.toPreviousMonth(action.yearMonth)
            is DashboardActions.OnNextMonthClicked ->
                calendarViewModel.toNextMonth(action.yearMonth)
        }
    }
    DashboardScreen(
        modifier = modifier,
        dashboardState = dashboardState,
        calendarUiState = calendarState,
        actions = handleDashboardActions,
    )
    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { errorMessage = null },
            title = { Text("Error") },
            text = { Text(errorMessage ?: "") },
            confirmButton = {
                TextButton(onClick = { errorMessage = null }) {
                    Text("OK")
                }
            }
        )
    }
}

