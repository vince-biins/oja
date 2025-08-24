package com.project.oja.presentation.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.oja.domain.repository.TimeLoggedRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository: TimeLoggedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardState())
    val uiState: StateFlow<DashboardState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<DashboardEffect>()
    val uiEffect: SharedFlow<DashboardEffect> = _uiEffect.asSharedFlow()

    init {
        onEvent(DashboardEvent.LoadDashboard)
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.DecrementHours -> reduceHours(event.hour)
            is DashboardEvent.IncrementHours -> addHours(hour = event.hour)
            DashboardEvent.LoadDashboard -> loadTimeLogs()

        }
    }

    private fun loadTimeLogs() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val logs = repository.getTimeLogs()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hourRecord = it.hourRecord.copy(
                            totalHours = logs.totalHours,
                            hoursTaken = logs.renderedTime
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
                _uiEffect.emit(DashboardEffect.ErrorMessage(e.message.toString()))
            }
        }
    }

    private fun addHours(hour: Float) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val logs = repository.addHours(hour)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hourRecord = it.hourRecord.copy(
                            totalHours = logs.totalHours,
                            hoursTaken = logs.renderedTime
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
                _uiEffect.emit(DashboardEffect.ErrorMessage(e.message.toString()))
            }
        }
    }

    private fun reduceHours(hour: Float) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val logs = repository.reduceHours(hour)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hourRecord = it.hourRecord.copy(
                            totalHours = logs.totalHours,
                            hoursTaken = logs.renderedTime
                        )
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
                _uiEffect.emit(DashboardEffect.ErrorMessage(e.message.toString()))
            }
        }
    }
}