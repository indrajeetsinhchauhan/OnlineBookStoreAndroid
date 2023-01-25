package com.indrajeetsinhchauhan.onlinebookstore.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.indrajeetsinhchauhan.onlinebookstore.data.BookModel
import com.indrajeetsinhchauhan.onlinebookstore.databinding.ActivityMainBinding
import com.indrajeetsinhchauhan.onlinebookstore.viewmodel.BookViewModel

class MainActivity : AppCompatActivity(), BookAdapter.BookListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: BookViewModel
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[BookViewModel::class.java]

        initAdapter()
        viewModel.fetchAllBooks()

        viewModel.bookModelListLiveData?.observe(this, Observer {
            if (it != null) {
                binding.rvList.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<BookModel>)
            } else {
                showToast("Something went wrong!")
            }
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun initAdapter() {
        adapter = BookAdapter()
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = adapter
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}