package com.ubaya.a160419062_ubayalibrary.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.databinding.BookReviewItemBinding
import com.ubaya.a160419062_ubayalibrary.model.Review

class BookReviewAdapter(val bookReviewList: ArrayList<Review>) : RecyclerView.Adapter<BookReviewAdapter.BookReviewViewHolder>(){
    class BookReviewViewHolder(var view: BookReviewItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookReviewViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BookReviewItemBinding>(inflater,
            R.layout.book_review_item, parent, false)
        return BookReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookReviewViewHolder, position: Int) {
        holder.view.review = bookReviewList[position]

    }

    override fun getItemCount()= bookReviewList.size

    fun updateBookReviewList(newBookReviewList: List<Review>){
        bookReviewList.clear()
        bookReviewList.addAll(newBookReviewList)
        notifyDataSetChanged()
    }
}