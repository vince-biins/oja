package com.project.oja.presentation.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.project.oja.base.enum.LoggedTime
import com.project.oja.data.model.HourRecord
import com.project.oja.data.model.calendar.CalendarUiState
import com.project.oja.presentation.components.AddHoursDialog
import com.project.oja.presentation.components.HalfCircleProgressBar
import com.project.oja.base.composables.OjaDropDown
import com.project.oja.presentation.components.sections.DateSection
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardState
import java.time.LocalDate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.oja.base.composables.OjaSlimDropDown
import com.project.oja.presentation.components.sections.DashboardCalendarSection

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardState: DashboardState,
    calendarUiState: CalendarUiState,
    actions: (DashboardActions) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }


    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        DashboardContent(
            modifier = Modifier.padding(innerPadding),
            dashboardState = dashboardState,
            calendarUiState = calendarUiState,
            actions = actions,
            showDialog = {
                showDialog = true
            }
        )

        if (showDialog) {
            AddHoursDialog(
                onDismissRequest = { showDialog = false },
                onConfirm = { hours ->
                    actions(DashboardActions.OnAddHourClicked(hours ?: 8.0F))
                    showDialog = false
                })
        }

    }
}

@Composable
fun DashboardContent(
    modifier: Modifier = Modifier,
    calendarUiState: CalendarUiState,
    dashboardState: DashboardState,
    actions: (DashboardActions) -> Unit,
    showDialog: () -> Unit,
) {
    val today = LocalDate.now()
    val dates = List(14) { today.plusDays(it.toLong()) }
    val hourSectionSize = 250.dp
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier.align(Alignment.End)
        ) {
            OjaSlimDropDown(itemList = LoggedTime.entries.map { it.description })
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HourSection(
                hourRecord = dashboardState.hourRecord,
                size = hourSectionSize,
                onSubtractIconClicked = { actions(DashboardActions.OnSubtractHourClicked(it)) },
                onAddIconClicked = {
                    //    actions(DashboardActions.OnAddHourClicked)
                    showDialog()
                }
            )
        }

        DashboardCalendarSection(calendarUiState = calendarUiState, actions = actions)

        Column { DateSection() }
    }
}

@Composable
fun HourSection(
    modifier: Modifier = Modifier,
    hourRecord: HourRecord,
    size: Dp,
    onAddIconClicked: (Float) -> Unit,
    onSubtractIconClicked: (Float) -> Unit,
) {
    Card(
        modifier = modifier
            .size(size),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp, start = 8.dp, end = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            HalfCircleProgressBar(
                hoursTaken = hourRecord.hoursTaken,
                totalHours = hourRecord.totalHours,
                modifier = Modifier.size(size.times(0.9f)),
                onAddIconClicked = {onAddIconClicked(8.0F)},
                onSubtractIconClicked = {onSubtractIconClicked(8.0F)},
            )
        }
    }
}