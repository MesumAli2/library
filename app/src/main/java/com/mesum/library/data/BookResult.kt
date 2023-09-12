package com.mesum.library.data

import com.mesum.library.data.network.ApiResponse

sealed class BooksResult {
    data class Success(val books : ApiResponse) : BooksResult()
    data class Error(val errorMessage: String) : BooksResult()
}
