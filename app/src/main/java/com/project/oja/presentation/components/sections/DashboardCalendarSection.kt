package com.project.oja.presentation.components.sections

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.project.oja.data.model.calendar.CalendarUiState
import com.project.oja.presentation.components.calendar.CalendarWidget
import com.project.oja.presentation.ui.dashboard.DashboardActions

@Composable
fun DashboardCalendarSection(
    modifier: Modifier = Modifier,
    calendarUiState: CalendarUiState,
    actions: (DashboardActions) -> Unit,
) {
    var isCalendarVisible by remember { mutableStateOf(true) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = { isCalendarVisible = !isCalendarVisible }) {
            Icon(
                imageVector = if (isCalendarVisible) Icons.Default.KeyboardArrowUp else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = if (isCalendarVisible) "Hide Calendar" else "Show Calendar"
            )
        }
    }
    AnimatedVisibility(   visible = isCalendarVisible,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {
        CalendarWidget(
            yearMonth = calendarUiState.yearMonth,
            dates = calendarUiState.dates,
            onPreviousMonthButtonClicked = { actions(DashboardActions.OnPreviousMonthClicked(it)) },
            onNextMonthButtonClicked = { actions(DashboardActions.OnNextMonthClicked(it)) },
            onDateClickListener = { actions(DashboardActions.OnDateItemClicked(it)) },
        )
    }

}