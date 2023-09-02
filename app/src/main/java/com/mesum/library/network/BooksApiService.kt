package com.mesum.library.network

import com.mesum.library.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("printType") printType: String
    ): ApiResponse
}