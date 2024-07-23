package com.shivayogi.openinapp

import android.content.Context
import com.shivayogi.openinapp.domain.usecase.GetDashboardDataUseCase
import com.shivayogi.openinapp.ui.viewmodels.DashboardViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.mockk.mockk

@Module
@InstallIn(ViewModelComponent::class)
object TestViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideGetDashboardDataUseCase(): GetDashboardDataUseCase {
        return mockk(relaxed = true)
    }

    @Provides
    @ViewModelScoped
    fun provideDashboardViewModel(
        getDashboardDataUseCase: GetDashboardDataUseCase,
        @ApplicationContext context: Context
    ): DashboardViewModel {
        return DashboardViewModel(getDashboardDataUseCase, context)
    }
}
