package com.shivayogi.openinapp.ui.screen.home


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shivayogi.openinapp.ui.navigation.BottomNavigationBar
import com.shivayogi.openinapp.ui.screen.profile.ProfileScreen
import com.shivayogi.openinapp.ui.screen.campaign.CampaignScreen
import com.shivayogi.openinapp.ui.screen.courses.CoursesScreen
import com.shivayogi.openinapp.ui.screen.linksScreen.AllLinksScreen
import com.shivayogi.openinapp.ui.screen.linksScreen.LinksScreen
import com.shivayogi.openinapp.ui.utils.Screens


@Composable
fun DashboardScreen() {
    val items = listOf("Links", "Courses", "Campaigns", "Profile")
    var selectedItem by remember { mutableStateOf(0) }

    var navigationSelectedItem by remember {
        mutableStateOf(Screens.Links.route)
    }
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = { index ->
                    selectedItem = index
                    navigationSelectedItem = when (index) {
                        0 -> Screens.Links.route
                        1 -> Screens.Courses.route
                        2 -> Screens.Campaigns.route
                        3 -> Screens.Profile.route
                        else -> Screens.Links.route

                    }
                    navController.navigate(navigationSelectedItem) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onFabClick = {

                    /* navigationSelectedItem = it
                     navController.navigate(navigationSelectedItem) {
                         popUpTo(navController.graph.findStartDestination().id) {
                             saveState = true
                         }
                         launchSingleTop = true
                         restoreState = true
                     }*/
                }
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = Screens.Links.route
            ) {
                composable(Screens.Links.route) { LinksScreen(navController) }
                composable(Screens.Courses.route) { CoursesScreen(navController) }
                composable(Screens.Campaigns.route) { CampaignScreen(navController) }
                composable(Screens.Profile.route) { ProfileScreen(navController) }
                composable(Screens.AddItem.route) { AddItemScreen(navController) }
                composable(Screens.AllLinks.route) { AllLinksScreen(navController,"") }

                composable(
                    "${Screens.AllLinks.route}/{linksType}",
                    arguments = listOf(
                        navArgument("linksType") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val linksType = backStackEntry.arguments?.getString("linksType")
                    linksType?.let {
                        AllLinksScreen(
                            navController = navController,
                            linksType = it
                        )
                    }
                }
            }
        }
    }
}
