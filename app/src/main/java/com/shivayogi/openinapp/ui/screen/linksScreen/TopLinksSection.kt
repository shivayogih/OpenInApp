package com.shivayogi.openinapp.ui.screen.linksScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.shivayogi.openinapp.R
import com.shivayogi.openinapp.domain.model.LinksData
import com.shivayogi.openinapp.ui.viewmodels.DashboardViewModel
import com.shivayogi.openinapp.ui.theme.BorderColor_GRAY
import com.shivayogi.openinapp.ui.theme.TextColor_BLACK
import com.shivayogi.openinapp.ui.theme.TextColor_BLUE
import com.shivayogi.openinapp.ui.theme.TextColor_GRAY
import com.shivayogi.openinapp.ui.theme.TextColor_WHITE
import com.shivayogi.openinapp.ui.theme.White
import com.shivayogi.openinapp.common.DateTimeUtils
import com.shivayogi.openinapp.ui.utils.DottedBorderTextWithCopy
import com.shivayogi.openinapp.ui.utils.Screens

@Composable
fun TopLinksSection(navController: NavController) {
    val viewModel: DashboardViewModel = hiltViewModel()
    var selectedTab by remember { mutableStateOf(0) }
    val topLinks by viewModel.topLinks.collectAsState()
    val recentLinks by viewModel.recentLinks.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightGray),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                indicator = { tabPositions ->
                    // Remove bottom line indicator
                    Box(
                        modifier = Modifier
                            .height(0.dp) // Set height to 0 to hide indicator
                    )
                },
                divider = { Box(modifier = Modifier.height(0.dp)) },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.5f)
                    .background(LightGray)
            ) {
                TabButton(
                    text = "Top Links",
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                TabButton(
                    text = "Recent Links",
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedButton(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(10),
                contentPadding = PaddingValues(10.dp),
                onClick = { /*TODO*/ },
                border = BorderStroke(2.dp, BorderColor_GRAY),
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = BorderColor_GRAY,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            0 -> LinksList(topLinks.take(4))
            1 -> LinksList(recentLinks.take(4))
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10),
            contentPadding = PaddingValues(16.dp),
            onClick = {
                when (selectedTab) {
                    0 -> {
                        navController.navigate("${Screens.AllLinks.route}?linksType=$topLinks")
                    }

                    1 -> {
                        navController.navigate("${Screens.AllLinks.route}?linksType=$recentLinks")
                    }
                }
            },
            border = BorderStroke(2.dp, BorderColor_GRAY),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_links),
                contentDescription = "Links Icon",
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                "View all Links",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun TabButton(text: String, selected: Boolean, onClick: () -> Unit) {

    Box(modifier = Modifier.background(LightGray)) {
        Surface(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(LightGray),
            color = if (selected) TextColor_BLUE else LightGray,
            contentColor = if (selected) TextColor_BLUE else LightGray
        ) {
            Box(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .background(Color.Transparent)
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = text, style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selected) TextColor_WHITE else TextColor_GRAY,
                )
            }
        }

    }

}

@Composable
fun LinksList(links: List<LinksData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        links.forEach { link ->
            EventItem(link)
        }
    }
}

@Composable
fun EventItem(eventData: LinksData?) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Set a fixed width for each card
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.background(White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Card(
                    modifier = Modifier
                        .size(48.dp) // Set a fixed width for each card
                        .padding(1.dp)
                        .weight(.5f)
                        .align(Alignment.CenterVertically),
                    elevation = CardDefaults.cardElevation(1.dp),
                ) {
                    eventData.original_image?.let {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            model = it,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }

                // Center Layout
                Column(
                    modifier = Modifier
                        .weight(2.5f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    eventData?.app?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelLarge,
                            color = TextColor_BLACK,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    eventData?.created_at?.let {
                        Text(
                            text = DateTimeUtils.convertDateTime(it),
                            style = MaterialTheme.typography.labelMedium,
                            color = TextColor_GRAY
                        )
                    }
                }
                // End Layout
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    eventData.total_clicks?.let {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.labelLarge,
                            color = TextColor_BLACK,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Clicks",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextColor_GRAY
                    )

                }
            }

            eventData.web_link?.let {
                DottedBorderTextWithCopy("", "", it)
            }

        }

    }
}