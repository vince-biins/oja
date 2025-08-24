package com.project.oja.presentation.ui.dashboard.viewmodel

import com.project.oja.data.model.HourRecord

data class DashboardState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val hourRecord: HourRecord = HourRecord(totalHours = 0.0F, hoursTaken = 0.0F)
)
