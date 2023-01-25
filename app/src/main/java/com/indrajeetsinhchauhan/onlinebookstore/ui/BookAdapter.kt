package com.indrajeetsinhchauhan.onlinebookstore.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.indrajeetsinhchauhan.onlinebookstore.R
import com.indrajeetsinhchauhan.onlinebookstore.data.BookModel
import com.indrajeetsinhchauhan.onlinebookstore.databinding.RvRowItemViewBinding
import com.squareup.picasso.Picasso

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var data: ArrayList<BookModel>? = null
    private lateinit var binding: RvRowItemViewBinding

    interface BookListener {
    }

    fun setData(list: ArrayList<BookModel>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = RvRowItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = data?.get(position)
        binding.tvBookTitle.text = item?.title
        binding.tvAuthorName.text = item?.author
        if (item?.avatar_url != null) {
            Picasso.with(binding.root.context)
                .load(item.avatar_url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageView)
        } else {
            Toast.makeText(binding.root.context, "Image URL is null or empty", Toast.LENGTH_SHORT)
                .show()
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(binding.root.context, BookDetailsActivity::class.java)
            intent.putExtra("user", item?.title)
            binding.root.context.startActivity(intent)
        }
    }

    class BookViewHolder(binding: RvRowItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}
