package com.shivayogi.openinapp.ui.utils

sealed class Screens(val route: String) {
    // val items = listOf("Links", "Courses", "Campaigns", "Profile")
    object Links : Screens("links")
    object Courses : Screens("courses")
    object Campaigns : Screens("campaigns")
    object Profile : Screens("profile")
    object AddItem : Screens("add_item")
    object AllLinks : Screens("all_links")
}