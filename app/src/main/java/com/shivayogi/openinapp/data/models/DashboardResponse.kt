package com.shivayogi.openinapp.data.models

import com.shivayogi.openinapp.domain.model.Data

data class DashboardResponse(
    val applied_campaign: Int? = null,
    val `data`: Data? = null,
    val extra_income: Double? = null,
    val links_created_today: Int? = null,
    val message: String? = null,
    val startTime: String? = null,
    val status: Boolean? = false,
    val statusCode: Int? = null,
    val support_whatsapp_number: String? = null,
    val today_clicks: Int? = 0,
    val top_location: String? = "",
    val top_source: String? = "",
    val total_clicks: Int? = 0,
    val total_links: Int? = 0
)
