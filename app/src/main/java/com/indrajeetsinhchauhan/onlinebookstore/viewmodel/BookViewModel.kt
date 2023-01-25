package com.indrajeetsinhchauhan.onlinebookstore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indrajeetsinhchauhan.onlinebookstore.data.BookModel
import com.indrajeetsinhchauhan.onlinebookstore.data.BookRepository

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private var bookRepository: BookRepository? = null
    var bookModelListLiveData: LiveData<List<BookModel>>? = null
    var bookModelLiveData: LiveData<BookModel>? = null

    init {
        bookRepository = BookRepository()
        bookModelListLiveData = MutableLiveData()
        bookModelLiveData = MutableLiveData()
    }

    fun fetchAllBooks() {
        bookModelListLiveData = bookRepository?.fetchAllBooks()
    }

    fun fetchBook(book: String) {
        bookModelLiveData = bookRepository?.fetchBook(book)
    }
}