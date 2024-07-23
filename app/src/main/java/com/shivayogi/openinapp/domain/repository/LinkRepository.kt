package com.shivayogi.openinapp.domain.repository

import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.common.Resource

interface LinkRepository {
    suspend fun getDashboardData(): Resource<DashboardResponse>
}