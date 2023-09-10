package com.mesum.library

import android.app.appsearch.SearchResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesum.library.data.BooksResult
import com.mesum.library.data.Item
import com.mesum.library.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel() : ViewModel() {
    private val repository: BookRepository = BookRepository()

    private val _books : MutableStateFlow<List<Item>>  = MutableStateFlow(listOf())
    val books = _books.asStateFlow()

    var searchedQuerryField : MutableStateFlow<String> = MutableStateFlow("")
    init {
        searchBooksInit()
    }

    fun searchBooks(query: String) {
         searchedQuerryField.value = query
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchBooks(query)
            when (result) {
                is BooksResult.Success -> {
                    // Handle the successful response (e.g., update UI)
                    val books = result.books.items
                    _books.value = books
                    // Do something with the list of books
                }
                is BooksResult.Error -> {
                    // Handle the error (e.g., show an error message)
                    val errorMessage = result.errorMessage
                }
            }
        }
    }


    private fun searchBooksInit() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchBooksInit()
            when (result) {
                is BooksResult.Success -> {
                    // Handle the successful response (e.g., update UI)
                    val books = result.books.items
                    _books.value = books
                    // Do something with the list of books
                }
                is BooksResult.Error -> {
                    // Handle the error (e.g., show an error message)
                    val errorMessage = result.errorMessage
                }
            }
        }
    }
}