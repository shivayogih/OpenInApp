package com.shivayogi.openinapp.ui.screen.analytics


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.ui.viewmodels.DashboardViewModel
import com.shivayogi.openinapp.ui.theme.White
import com.shivayogi.openinapp.common.DateRangePicker
import com.shivayogi.openinapp.ui.utils.DisplayChart


@Composable
fun OverviewChart() {

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(RoundedCornerShape(16.dp))
    ) {
        val cardHeight = maxWidth * .65f
        Card(
            modifier = Modifier
                .width(maxWidth)
                .height(cardHeight)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(2.dp),
        ) {
            Column(
                modifier = Modifier
                    .background(White)
                    .padding(8.dp, 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Overview",
                        modifier = Modifier,
                        style = MaterialTheme.typography.displayLarge
                    )
                    DateRangePicker()

                }

                Spacer(modifier = Modifier.width(8.dp))
                DisplayChart()
            }


        }
    }

}


