package com.project.oja.domain.service

import android.util.Log
import com.project.oja.data.model.TimeLogged

class TimeLoggedService {

    private var hours = TimeLogged(
        id = 1,
        renderedTime = 8.0F,
        totalHours = 300.0F
    )

    fun getTimeLogged() : TimeLogged {
        return hours
    }

    suspend fun addHour(hour: Float) : TimeLogged {
       hours = hours.copy(
            renderedTime = hour + hours.renderedTime
        )

        return hours
    }

    suspend fun reduceHour(hour: Float) : TimeLogged {
        hours = hours.copy(
            renderedTime = hours.renderedTime - hour
        )
        return hours
    }
}