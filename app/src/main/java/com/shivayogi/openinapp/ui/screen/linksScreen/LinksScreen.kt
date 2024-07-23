package com.shivayogi.openinapp.ui.screen.linksScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shivayogi.openinapp.R
import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.ui.screen.home.GreetingSection
import com.shivayogi.openinapp.ui.screen.analytics.AnalyticsView
import com.shivayogi.openinapp.ui.viewmodels.DashboardViewModel
import com.shivayogi.openinapp.ui.theme.*
import com.shivayogi.openinapp.ui.utils.ActionButtons
import com.shivayogi.openinapp.utils.openWhatsApp

@Composable
fun LinksScreen(navController: NavController) {
    val viewModel: DashboardViewModel = hiltViewModel()

    // Collect the state from the ViewModel
    val dashboardResponse by viewModel.dashboardResponse

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val boxHeight = maxHeight * 0.15f
        Box(
            modifier = Modifier
                .height(boxHeight)
                .fillMaxWidth()
                .background(BlueSurface)
        ) {
            PageHeader()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = boxHeight * 0.85f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(LightGray)
        ) {
            LinksContent(dashboardResponse, navController)
        }
    }
}

@Composable
fun LinksContent(dashBoardData: DashboardResponse?, navController: NavController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(top = 8.dp)
            .background(LightGray)
            .verticalScroll(rememberScrollState())
    )
    {
        GreetingSection("Shivayogi")
        Spacer(modifier = Modifier.height(6.dp))
        AnalyticsView(dashBoardData)
        Spacer(modifier = Modifier.height(16.dp))
        TopLinksSection(navController)
        Spacer(modifier = Modifier.height(16.dp))
        ActionButtons(
            "Talk with us",
            color_background_green,
            color_green,
            TextColor_BLACK,
            R.drawable.ic_whatsapp,
            color_green
        ) {
            //WhatsApp link action Intent
            dashBoardData?.support_whatsapp_number?.let { openWhatsApp(context, it) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        ActionButtons(
            "Frequently Asked Questions",
            color_background_blue,
            color_Blue,
            TextColor_BLACK,
            R.drawable.ic_help,
            color_Blue
        ) {
            //HelpView link action Intent
        }
        Spacer(modifier = Modifier.height(200.dp))

    }
}

@Composable
fun PageHeader() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Dashboard",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge
        )

        IconButton(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(BlueSurface)
                .shadow(elevation = 0.dp, shape = RectangleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home_menu),
                contentDescription = "Clickable Button",
                tint = Color.White,
            )
        }
    }
}
