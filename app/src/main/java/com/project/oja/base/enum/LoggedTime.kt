package com.project.oja.base.enum

enum class LoggedTime(val value: Float) {
    EIGHT_HOURS(8.0f),
    ONE_HOUR(1.0f),
    HALF_HOUR(0.5f),
    CUSTOM(0.0f);

    val description: String
        get() = if (this == CUSTOM) "Custom" else when (this) {
            EIGHT_HOURS -> "8 hours"
            ONE_HOUR -> "1 hour"
            HALF_HOUR -> "30 mins"
            else -> ""
        }
}