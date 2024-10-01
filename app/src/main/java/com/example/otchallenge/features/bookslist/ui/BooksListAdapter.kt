package com.example.otchallenge.features.bookslist.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.otchallenge.features.bookslist.BookPresentationObject

class BooksListAdapter : ListAdapter<BookPresentationObject, BookViewHolder>(BookDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object BookDiffCallback : DiffUtil.ItemCallback<BookPresentationObject>() {
        override fun areItemsTheSame(oldItem: BookPresentationObject, newItem: BookPresentationObject): Boolean {
            return oldItem.bookUri == newItem.bookUri
        }

        override fun areContentsTheSame(oldItem: BookPresentationObject, newItem: BookPresentationObject): Boolean {
            return oldItem == newItem
        }
    }
}