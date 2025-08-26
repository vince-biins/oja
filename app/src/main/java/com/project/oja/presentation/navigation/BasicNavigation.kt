package com.project.oja.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.project.oja.presentation.components.calendar.CalendarViewModel
import com.project.oja.presentation.ui.dashboard.DashboardRoot
import com.project.oja.presentation.ui.dashboard.viewmodel.DashboardViewModel
import com.project.oja.presentation.ui.forms.DateFormRoot
import org.koin.androidx.compose.koinViewModel

@Composable
fun BasicNavigation(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack<Screens>(Screens.Dashboard)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull()},
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator(),
        ),
        entryProvider = entryProvider {

                entry<Screens.Dashboard> {
                    val dashboardViewModel = koinViewModel<DashboardViewModel>()
                    val calendarViewModel = koinViewModel<CalendarViewModel>()
                    DashboardRoot(
                        backStack = backStack,
                        dashboardViewModel = dashboardViewModel,
                        calendarViewModel = calendarViewModel
                    )
                }

                entry<Screens.Detail>  {
                    DateFormRoot(it.id)
                }

        }

    )
}