package com.project.oja.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screens: NavKey{
    @Serializable
    data object Dashboard : Screens()
    @Serializable
    data class Detail(val id: String) : Screens()
}