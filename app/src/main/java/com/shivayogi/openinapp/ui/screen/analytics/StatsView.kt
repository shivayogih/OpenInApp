package com.shivayogi.openinapp.ui.screen.analytics



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shivayogi.openinapp.R
import com.shivayogi.openinapp.data.models.DashboardResponse

data class StatsItem(
    val imageResource: Int, // Assuming you're using drawable resource IDs
    val type: String,
    val values: String // You can change the type if necessary
)

@Composable
fun StatsView(dashBoardData: DashboardResponse?) {

    val items = listOf(
        StatsItem(
            R.drawable.ic_clicks, "Today’s clicks",
            (dashBoardData?.today_clicks?.toString() ?: "0")
        ),
        StatsItem(
            R.drawable.ic_location, "Top Location",
            (dashBoardData?.top_location ?: "")
        ),
        StatsItem(
            R.drawable.ic_source, "Top Source",
            (dashBoardData?.top_source ?: "")
        )
    )
    ScrollableRowWithEqualItems(items)

}


@Composable
fun ScrollableRowWithEqualItems(items: List<StatsItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(items) { item ->
            StatsCard(item)
        }
    }

}
