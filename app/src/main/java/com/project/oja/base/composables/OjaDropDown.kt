package com.project.oja.base.composables

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.oja.base.enum.LoggedTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OjaDropDown(modifier: Modifier = Modifier, itemList: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    val itemLists = itemList

    var selectedItem by remember { mutableStateOf(itemLists.first()) }
    Box(
        modifier = modifier.padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.width(150.dp),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select time", color = MaterialTheme.colorScheme.primary) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)

                    .background(Color.Transparent, RoundedCornerShape(8.dp))
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )

            ExposedDropdownMenu(
                containerColor = Color.Transparent,
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                itemLists.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selectedItem = item
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        modifier = Modifier.background(Color.Transparent)
                    )
                }
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> OjaSlimDropDown(
    modifier: Modifier = Modifier,
    itemList: List<T>,
    selectedItem: T = itemList.first(),
    onValueChanged: (T) -> Unit,
    labelMapper: (T) -> String
) {
    var expanded by remember { mutableStateOf(false) }
    var currentSelected by remember { mutableStateOf(selectedItem) }

    val pillShape = RoundedCornerShape(60)

    Box(
        modifier = modifier.padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.background(Color.Transparent)
        ) {

            BasicTextField(
                value = labelMapper(currentSelected),
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(Color.Transparent, pillShape)
                            .border(1.dp, MaterialTheme.colorScheme.primary, pillShape)
                            .height(28.dp)
                            .width(100.dp)
                            .padding(start = 8.dp, end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            innerTextField()
                        }
                        val rotationAngle by animateFloatAsState(
                            targetValue = if (expanded) 180f else 0f,
                            label = "DropdownArrowRotation"
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown Arrow",
                            modifier = Modifier.rotate(rotationAngle)
                        )
                    }
                }
            )

            ExposedDropdownMenu(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                shadowElevation = 0.dp,
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.Transparent, RoundedCornerShape(4))
                    .padding(top = 2.dp)
                    .border(1.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(8))
            ) {
                itemList.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                labelMapper(item),
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        },
                        onClick = {
                            currentSelected = item
                            expanded = false
                            onValueChanged(item)
                        },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp),
                        modifier = Modifier
                            .background(Color.Transparent, RoundedCornerShape(4))
                            .height(28.dp),
                    )
                }
            }
        }
    }
}