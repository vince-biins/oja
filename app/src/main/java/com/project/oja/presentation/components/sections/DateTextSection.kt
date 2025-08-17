package com.project.oja.presentation.components.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun DateSection(
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val startDate = LocalDate.of(today.year, 1, 1)
    val endDate = today.plusMonths(3)

    val dates = generateSequence(startDate) { it.plusDays(1) }
        .takeWhile { !it.isAfter(endDate) }
        .toList()

    val todayIndex = dates.indexOf(today).coerceAtLeast(0)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var selectedDate by remember { mutableStateOf(today) }
    LaunchedEffect(Unit) {
        listState.scrollToItem(todayIndex)
    }
    val firstVisible = listState.firstVisibleItemIndex
    val lastVisible = firstVisible + listState.layoutInfo.visibleItemsInfo.size - 1

    val isTodayVisible = todayIndex in firstVisible..lastVisible
    val isInPast = lastVisible < todayIndex
    Box(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            state = listState,
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(dates.size) { index ->
                val date = dates[index]
                val isToday = date == today
                val isSelected = date == selectedDate
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = when {
                                isSelected -> MaterialTheme.colorScheme.secondary
                                isToday -> MaterialTheme.colorScheme.primary
                                else -> MaterialTheme.colorScheme.background
                            },
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable {   selectedDate = date }
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "${date.monthValue}/${date.dayOfMonth}",
                        color = when {
                            isSelected -> MaterialTheme.colorScheme.onSecondary
                            isToday -> MaterialTheme.colorScheme.onPrimary
                            else -> Color.Black
                        },
                        fontWeight = if (isToday || isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
        if (!isTodayVisible) {
            val alignment = if (isInPast) AbsoluteAlignment.TopLeft else AbsoluteAlignment.TopRight
            val arrowImageVector =
                if (isInPast) Icons.AutoMirrored.Rounded.ArrowForward else Icons.AutoMirrored.Rounded.ArrowBack
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
                    .align(alignment)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(40.dp)
                    )
            ) {
                IconButton(
                    modifier = Modifier
                        .padding(top = 2.dp),

                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(todayIndex)
                        }
                    }
                ) {
                    Icon(
                        imageVector = arrowImageVector,
                        contentDescription = "Go to Today",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}