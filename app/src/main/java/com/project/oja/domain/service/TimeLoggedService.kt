package com.project.oja.domain.service

import com.project.oja.data.model.TimeLogged

class TimeLoggedService {

    val hours = TimeLogged(
        id = 1,
        renderedTime = 8.0F,
        totalHours = 300.0F
    )



    fun getTimeLogged() : TimeLogged {
        return hours;
    }

    suspend fun addHour(hour: Float) : TimeLogged {
       return hours.copy(
            renderedTime = hour + hours.renderedTime
        )
    }

    suspend fun reduceHour(hour: Float) : TimeLogged {
        return hours.copy(
            renderedTime = hours.renderedTime - hour
        )
    }
}