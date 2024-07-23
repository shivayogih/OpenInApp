package com.shivayogi.openinapp.ui.screen.analytics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shivayogi.openinapp.R
import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.ui.theme.BorderColor_GRAY

@Composable
fun AnalyticsView(dashBoardData: DashboardResponse?) {
    Column {
        OverviewChart(dashBoardData)
        Spacer(modifier = Modifier.width(16.dp))
        StatsView(dashBoardData)
        Spacer(modifier = Modifier.width(20.dp))

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp),
            shape = RoundedCornerShape(10),
            contentPadding = PaddingValues(16.dp),
            onClick = { /*TODO*/ },
            border = BorderStroke(2.dp, BorderColor_GRAY),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chart_arrows),
                contentDescription = "Phone Icon",
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                "View Analytics",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}




