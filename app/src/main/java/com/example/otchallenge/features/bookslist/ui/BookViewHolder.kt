package com.example.otchallenge.features.bookslist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.otchallenge.databinding.BookItemLayoutBinding
import com.example.otchallenge.features.bookslist.BookPresentationObject


class BookViewHolder(private val binding: BookItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(book: BookPresentationObject) {
        with(binding) {
            title.text = book.title
            author.text = book.author
            description.text = book.description
            image.contentDescription = book.title
            Glide.with(itemView).load(book.bookImage).into(image)
        }
    }

    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            return BookViewHolder(
                BookItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}