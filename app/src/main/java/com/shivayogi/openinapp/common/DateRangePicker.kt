package com.shivayogi.openinapp.common

import androidx.compose.runtime.Composable
import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shivayogi.openinapp.ui.theme.BorderColor_GRAY


@Composable
fun DateRangePicker() {
    // Calculate default dates
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val endDate = calendar.time

    calendar.add(Calendar.MONTH, -1)
    val startDate = calendar.time

    var selectedStartDate by remember { mutableStateOf(startDate) }
    var selectedEndDate by remember { mutableStateOf(endDate) }
    var isStartDatePickerOpen by remember { mutableStateOf(false) }
    var isEndDatePickerOpen by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .border(width = 1.dp, color = BorderColor_GRAY, shape = RoundedCornerShape(4.dp))
                .padding(4.dp)
                .clickable {
                    // Open the start date picker
                    isStartDatePickerOpen = true
                }
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "${DateTimeUtils.formatDate(selectedStartDate)} - ${
                        DateTimeUtils.formatDate(
                            selectedEndDate
                        )
                    }",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color.Gray
                )
            }
        }

        // Show start date picker when needed
        if (isStartDatePickerOpen) {
            Toast.makeText(context, "Select Start Date", Toast.LENGTH_SHORT).show()
            ShowDatePickerDialog(
                initialDate = selectedStartDate,
                title = "Select Start Date",
                onDateSelected = { date ->
                    selectedStartDate = date
                    isStartDatePickerOpen = false
                    // Open the end date picker if the end date is not set or before the start date
                    if (selectedEndDate.before(date)) {
                        selectedEndDate = date
                    }
                    isEndDatePickerOpen = true
                }
            )
        }
        // Show end date picker when needed

        // Show end date picker when needed
        if (isEndDatePickerOpen) {
            Toast.makeText(context, "Select End Date", Toast.LENGTH_SHORT).show()
            ShowDatePickerDialog(
                initialDate = selectedEndDate,
                title = "Select End Date",
                onDateSelected = { date ->
                    selectedEndDate = date
                    isEndDatePickerOpen = false
                }
            )
        }
    }
}

@Composable
fun ShowDatePickerDialog(
    initialDate: Date,
    title: String,
    onDateSelected: (Date) -> Unit
) {
    val calendar = Calendar.getInstance().apply { time = initialDate }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val context = LocalContext.current

    DatePickerDialog( context,{ _, selectedYear, selectedMonth, selectedDay ->
        val selectedDate = Calendar.getInstance().apply {
            set(Calendar.YEAR, selectedYear)
            set(Calendar.MONTH, selectedMonth)
            set(Calendar.DAY_OF_MONTH, selectedDay)
        }.time
        onDateSelected(selectedDate)
    }, year, month, day).show()

}