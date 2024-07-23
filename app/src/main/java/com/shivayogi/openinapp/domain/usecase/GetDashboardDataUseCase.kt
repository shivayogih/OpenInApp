package com.shivayogi.openinapp.domain.usecase

import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.domain.repository.LinkRepository
import com.shivayogi.openinapp.common.Resource
import javax.inject.Inject

class GetDashboardDataUseCase @Inject constructor(
    private val repository: LinkRepository
) {
    suspend fun findDashBoardData(): Resource<DashboardResponse> {
        return repository.getDashboardData()
    }
}