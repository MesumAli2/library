package com.mesum.library.repository

import android.app.appsearch.SearchResult
import com.mesum.library.data.ApiResponse
import com.mesum.library.data.BooksResult
import com.mesum.library.data.Item
import com.mesum.library.network.BooksApiService
import com.mesum.library.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class BookRepository {
    private val apiService = RetrofitClient.retrofit.create(BooksApiService::class.java)
    suspend fun searchBooks( query: String ): BooksResult {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchBooks(query = query, printType = "books")
                BooksResult.Success(response)

            } catch (e : HttpException){
                BooksResult.Error("Http error ${e.code()}")

            }catch (e : IOException){
                BooksResult.Error("IO Exception ${e.message.toString()}")
            }
        }
    }

    suspend fun searchBooksInit( ): BooksResult {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.searchBooksInit()
                BooksResult.Success(response)

            } catch (e : HttpException){
                BooksResult.Error("Http error ${e.code()}")

            }catch (e : IOException){
                BooksResult.Error("IO Exception ${e.message.toString()}")
            }
        }
    }

}