package com.shivayogi.openinapp.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivayogi.openinapp.data.models.DashboardResponse
import com.shivayogi.openinapp.domain.model.LinksData
import com.shivayogi.openinapp.common.Resource
import com.shivayogi.openinapp.data.local.TokenManager
import com.shivayogi.openinapp.domain.usecase.GetDashboardDataUseCase
import com.shivayogi.openinapp.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDashboardDataUseCase: GetDashboardDataUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var dashboardResponse = mutableStateOf<DashboardResponse?>(null)
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private val _topLinks = MutableStateFlow<List<LinksData>>(emptyList())
    val topLinks: StateFlow<List<LinksData>> get() = _topLinks

    private val _recentLinks = MutableStateFlow<List<LinksData>>(emptyList())
    val recentLinks: StateFlow<List<LinksData>> get() = _recentLinks

    init {
        TokenManager.saveToken(TokenManager.TOKEN_VALUE)
        TokenManager.getToken()?.let { fetchDashBoardData() }
    }

    fun fetchDashBoardData() {
        viewModelScope.launch {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                loadError.value = "No network connection or internet access"
                return@launch
            }
            isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    getDashboardDataUseCase.findDashBoardData()
                }
                handleResponse(response)
            } catch (e: Exception) {
                loadError.value = "An unexpected error occurred: ${e.localizedMessage}"
                isLoading.value = false
            }
        }
    }

    private fun handleResponse(response: Resource<DashboardResponse>) {
        when (response) {
            is Resource.Success -> {
                loadError.value = ""
                isLoading.value = false
                if (response.data?.status == true) {
                    _topLinks.value = response.data.data?.top_links ?: emptyList()
                    _recentLinks.value = response.data.data?.recent_links ?: emptyList()
                    dashboardResponse.value = response.data
                }
            }

            is Resource.Error -> {
                loadError.value = response.message ?: "An unknown error occurred"
                isLoading.value = false
            }

            is Resource.Loading -> {
                isLoading.value = true
            }
        }
    }
}