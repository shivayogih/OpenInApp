package com.shivayogi.openinapp.ui.screen.analytics


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shivayogi.openinapp.ui.theme.White

@Composable
fun StatsCard(statsItem: StatsItem?, modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
            contentColor = White
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(White)
                .padding(16.dp, 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            statsItem?.imageResource?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = statsItem.type,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = statsItem?.values ?: "", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = statsItem?.type ?: "", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
