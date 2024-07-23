package com.shivayogi.openinapp.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AddItemScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(), // Fill the entire parent size
        contentAlignment = Alignment.Center // Center the content inside the Box
    ) {
        Text(
            text = "Add New Item",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            )
        )
    }
}