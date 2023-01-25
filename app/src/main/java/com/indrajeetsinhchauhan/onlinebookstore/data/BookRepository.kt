package com.indrajeetsinhchauhan.onlinebookstore.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indrajeetsinhchauhan.onlinebookstore.network.ApiClient
import com.indrajeetsinhchauhan.onlinebookstore.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository {
    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllBooks(): LiveData<List<BookModel>> {
        val data = MutableLiveData<List<BookModel>>()
        apiInterface?.fetchAllBooks()?.enqueue(object : Callback<List<BookModel>> {
            override fun onResponse(
                call: Call<List<BookModel>>,
                response: Response<List<BookModel>>
            ) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }

    fun fetchBook(book: String): LiveData<BookModel> {
        val data = MutableLiveData<BookModel>()
        apiInterface?.fetchBook(book)?.enqueue(object : Callback<BookModel> {
            override fun onResponse(call: Call<BookModel>, response: Response<BookModel>) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<BookModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}