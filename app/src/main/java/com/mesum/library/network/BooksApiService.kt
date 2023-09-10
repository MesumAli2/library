package com.mesum.library.network

import com.mesum.library.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface BooksApiService {
    @GET("volumes")
    suspend fun searchBooks(

        @Query("q") query: String = "''",
        @Query("printType") printType: String
    ): ApiResponse



        @GET
        suspend fun searchBooksInit(@Url url: String = "https://www.googleapis.com/books/v1/volumes?q=''&printType=books"): ApiResponse


}