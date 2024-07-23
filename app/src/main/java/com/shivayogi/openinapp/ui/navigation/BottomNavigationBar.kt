package com.shivayogi.openinapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.shivayogi.openinapp.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Alignment
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.shivayogi.openinapp.ui.theme.BlueSurface
import com.shivayogi.openinapp.ui.theme.color_active
import com.shivayogi.openinapp.ui.theme.color_inactive
import com.shivayogi.openinapp.ui.utils.Screens

@Composable
fun BottomNavigationBar(
    items: List<String>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    onFabClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .zIndex(0f) // Ensure the bottom bar and FAB are properly layered
    ) {
        // BottomNavigation with space for the FAB
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, item ->
                if (index == 2) {
                    Spacer(modifier = Modifier.width(72.dp)) // Space for FAB
                }

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = getIconForItem(item)),
                            contentDescription = item,
                            tint = if (selectedItem == index) color_active else color_inactive
                        )
                    },
                    label = {
                        Text(
                            text = item,
                            style = TextStyle(
                                color = if (selectedItem == index) color_active else color_inactive, // Customize text color
                                fontSize = 12.sp // Customize text size if needed
                            )
                        )
                    },
                    selected = selectedItem == index,
                    onClick = {
                        onItemSelected(index)
                    },
                    modifier = Modifier.weight(1f),
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent, // No background color for active item
                        selectedIconColor = color_active,
                        unselectedIconColor = color_inactive,
                        selectedTextColor = color_active,
                        unselectedTextColor = color_inactive
                    )
                )
            }
        }


        val elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp, // Set the default elevation
            pressedElevation = 0.dp, // Elevation when pressed
            focusedElevation = 0.dp, // Elevation when focused
            hoveredElevation = 0.dp // Elevation when hovered
        )

        // FloatingActionButton positioned between 2nd and 3rd items
        Surface(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-25).dp)
                .zIndex(4f)
                // Set the background color
                .border(6.dp, Color.White, shape = CircleShape),
            shape = RoundedCornerShape(100)
        ) {
            LargeFloatingActionButton(
                onClick = { onFabClick(Screens.AddItem.route) },
                shape = RoundedCornerShape(100),
                elevation = elevation,
                containerColor = BlueSurface,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.BottomCenter)
                // Ensure the FAB is above the navigation items
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }

    }
}

@Composable
fun getIconForItem(item: String): Int {
    return when (item) {
        "Links" -> R.drawable.ic_links
        "Courses" -> R.drawable.ic_courses
        "Campaigns" -> R.drawable.ic_campaigns
        "Profile" -> R.drawable.ic_profile
        else -> R.drawable.ic_links
    }
}