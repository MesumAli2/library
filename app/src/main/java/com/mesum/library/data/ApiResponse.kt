package com.mesum.library.data

data class ApiResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)



sealed class BooksResult {
    data class Success(val books : ApiResponse) : BooksResult()
    data class Error(val errorMessage: String) : BooksResult()
}



sealed class NetworkStatus {
    object Connected : NetworkStatus()
    object Disconnected : NetworkStatus()
}