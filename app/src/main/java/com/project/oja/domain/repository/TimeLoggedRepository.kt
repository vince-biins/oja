package com.project.oja.domain.repository

import com.project.oja.data.model.TimeLogged

interface TimeLoggedRepository {
    fun getTimeLogs() : TimeLogged
    suspend fun addHours(hour: Float) : TimeLogged
    suspend fun reduceHours(hour: Float) : TimeLogged
}