package com.shivayogi.openinapp.data.api

import com.shivayogi.openinapp.data.models.DashboardResponse
import retrofit2.http.GET


interface ApiService {

    @GET("/api/v1/dashboardNew")
    suspend fun getDashboardData(): DashboardResponse
}