package com.shivayogi.openinapp.data.repository

import com.shivayogi.openinapp.data.api.ApiService
import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.common.Resource
import com.shivayogi.openinapp.domain.repository.LinkRepository
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class DashboardRepository @Inject constructor(private val apiService: ApiService) : LinkRepository {
    override suspend fun getDashboardData(): Resource<DashboardResponse> {
        val response = try {
            apiService.getDashboardData()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}