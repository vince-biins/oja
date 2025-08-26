package com.project.oja.presentation.ui.dashboard.viewmodel


sealed class DashboardEffect {
    data class ErrorMessage(val message: String) : DashboardEffect()
}