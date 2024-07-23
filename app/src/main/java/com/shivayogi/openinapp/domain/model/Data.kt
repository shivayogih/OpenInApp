package com.shivayogi.openinapp.domain.model

data class Data(
    val favourite_links: List<Any>,
    val overall_url_chart: Any,
    val recent_links: List<LinksData>,
    val top_links: List<LinksData>
)