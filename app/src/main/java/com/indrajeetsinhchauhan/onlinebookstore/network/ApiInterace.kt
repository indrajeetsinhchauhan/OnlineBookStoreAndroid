package com.indrajeetsinhchauhan.onlinebookstore.network

import com.indrajeetsinhchauhan.onlinebookstore.data.BookModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/books")
    fun fetchAllBooks(): Call<List<BookModel>>

    @GET("/books/{book}")
    fun fetchBook(@Path("book") book: String): Call<BookModel>
}