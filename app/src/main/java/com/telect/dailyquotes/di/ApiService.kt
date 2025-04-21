package com.telect.dailyquotes.di

import com.telect.dailyquotes.model.Quote
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("quotes")
    suspend fun getQuotes(): Response<List<Quote>>

    companion object {
        const val BASE_URL: String = "https://zenquotes.io/api/"
    }
}