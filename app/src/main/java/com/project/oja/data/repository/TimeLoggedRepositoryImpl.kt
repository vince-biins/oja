package com.project.oja.data.repository

import com.project.oja.base.exception.InvalidException
import com.project.oja.data.model.TimeLogged
import com.project.oja.domain.repository.TimeLoggedRepository
import com.project.oja.domain.service.TimeLoggedService

class TimeLoggedRepositoryImpl(
    private val service: TimeLoggedService
) : TimeLoggedRepository {
    override fun getTimeLogs(): TimeLogged = service.getTimeLogged()

    override suspend fun addHours(hour: Float): TimeLogged = service.addHour(hour)

    override suspend fun reduceHours(hour: Float): TimeLogged =
        service.getTimeLogged()
            .takeIf { current -> hour <= current.renderedTime }
            ?.let { service.reduceHour(hour) }
            ?: throw InvalidException("Cannot reduce more hours ($hour) than logged.")
}