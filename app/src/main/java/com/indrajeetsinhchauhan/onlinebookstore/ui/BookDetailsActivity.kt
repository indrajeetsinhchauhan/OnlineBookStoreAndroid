package com.indrajeetsinhchauhan.onlinebookstore.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.indrajeetsinhchauhan.onlinebookstore.databinding.ActivityBookDetailsBinding
import com.indrajeetsinhchauhan.onlinebookstore.viewmodel.BookViewModel

class BookDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailsBinding
    private lateinit var viewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[BookViewModel::class.java]

        val extras = intent.extras
        viewModel.fetchBook(extras!!.getString("title", ""))
        viewModel.bookModelLiveData?.observe(this) {
            if (it != null) {
                binding.tvTitle.text = it.title
                binding.tvAuthor.text = it.authors
                binding.tvPageCount.text = it.pageCount
                binding.tvIsbn.text = it.isbn
                binding.tvPublishDate.text = it.publishedDate
                binding.tvStatus.text = it.status
                binding.tvCategories.text = it.categories
                binding.tvDescription.text = it.longDescription
            } else {
                showToast("Something went wrong")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}