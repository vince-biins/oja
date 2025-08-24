package com.project.oja.presentation.ui.dashboard.viewmodel

sealed class DashboardEvent {
    data object LoadDashboard: DashboardEvent()
    data class IncrementHours(val hour: Float) : DashboardEvent()
    data class DecrementHours(val hour: Float) : DashboardEvent()
}

sealed class DashboardEffect {
    data class ErrorMessage(val message: String) : DashboardEffect()
}