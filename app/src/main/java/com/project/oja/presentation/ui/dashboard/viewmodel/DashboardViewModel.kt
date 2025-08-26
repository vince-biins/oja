package com.project.oja.presentation.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.oja.domain.repository.TimeLoggedRepository
import com.project.oja.presentation.ui.dashboard.DashboardActions
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
        loadTimeLogs()
    }

    fun onAction(action: DashboardActions) {
        when (action) {
            is DashboardActions.OnAddHourClicked -> addHours(action.hour)
            is DashboardActions.OnSubtractHourClicked -> reduceHours(action.hour)
            else -> Unit
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