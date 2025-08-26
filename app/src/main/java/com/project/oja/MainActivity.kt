package com.project.oja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.oja.base.theme.OJATheme
import com.project.oja.presentation.navigation.BasicNavigation
import com.project.oja.presentation.ui.dashboard.DashboardRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OJATheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BasicNavigation()
                }
            }
        }
    }
}

