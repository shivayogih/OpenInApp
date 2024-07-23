package com.shivayogi.openinapp.di

import com.shivayogi.openinapp.domain.repository.LinkRepository
import com.shivayogi.openinapp.domain.usecase.GetDashboardDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetDashboardDataUseCase(
        repository: LinkRepository
    ): GetDashboardDataUseCase {
        return GetDashboardDataUseCase(repository)
    }
}