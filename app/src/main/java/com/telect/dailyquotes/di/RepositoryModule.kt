package com.telect.dailyquotes.di

import com.telect.dailyquotes.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    // This module provides the repository dependencies for the application.
    // It is annotated with @Module and @InstallIn to specify that it should be installed in the SingletonComponent.
    // The @Provides annotation is used to indicate that the method provides a dependency.
    // The method returns an instance of QuoteRepository, which is a repository interface for managing quotes.
    // The method takes a QuoteApi instance as a parameter, which is used to fetch quotes from an API.
    // The method creates and returns an instance of QuoteRepositoryImpl, which implements the QuoteRepository interface.

    @Provides
    @Singleton
    fun getRepository(apiService: ApiService): DataRepository {
        return DataRepository(apiService)
    }
}