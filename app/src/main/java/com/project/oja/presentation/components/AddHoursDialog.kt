package com.project.oja.presentation.components


import androidx.compose.animation.animateContentSize

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.project.oja.base.composables.OjaTextField
import com.project.oja.base.enum.LoggedTime

@Composable
fun AddHoursDialog(
    onDismissRequest: () -> Unit,
    onConfirm: (Float?) -> Unit
) {
    var showCustomInput by remember { mutableStateOf(false) }
    var customInput by remember { mutableStateOf("") }
    val fixedLabel = "Add 8hrs"

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .height(IntrinsicSize.Min), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = fixedLabel,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))


            Box(modifier = Modifier.clickable { showCustomInput = !showCustomInput }
                .align(Alignment.Start)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Custom",

                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier
                            .padding(4.dp)
                    )
                    Icon(
                        imageVector = if (!showCustomInput) Icons.Rounded.KeyboardArrowDown else Icons.Rounded.KeyboardArrowUp,
                        contentDescription = "Add custom time",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize() // keep parent stable
                ) {
                    androidx.compose.animation.AnimatedVisibility(
                        visible = showCustomInput,
                        enter = slideInVertically { -it } + fadeIn(),
                        exit = slideOutVertically { -it } + fadeOut()
                    ) {
                        OjaTextField(
                            value = customInput,
                            onValueChange = { customInput = it },
                            placeholder = "\"e.g 1 or 1.5\"",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth(),
                            label = "Input hour/s"
                        )
                    }
                }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val hours = customInput.toFloatOrNull()
                        if (showCustomInput) {
                            onConfirm(hours)
                        } else {
                            onConfirm(LoggedTime.EIGHT_HOURS.value)
                        }
                    }
                ) {
                    Text("Confirm")
                }
            }
        }
        }
    }
}
