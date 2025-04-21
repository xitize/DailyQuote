package com.telect.dailyquotes.repository

import com.telect.dailyquotes.di.ApiService

class DataRepository(val apiService: ApiService) {
    /**
     * Fetches a list of quotes from the API.
     *
     * @return A list of quotes.
     */

    suspend fun getQuotes() = apiService.getQuotes().body() ?: emptyList()
}