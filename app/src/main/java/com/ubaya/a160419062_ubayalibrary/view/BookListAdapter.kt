package com.ubaya.a160419062_ubayalibrary.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.databinding.BookListItemBinding
import com.ubaya.a160419062_ubayalibrary.model.Book
import com.ubaya.a160419062_ubayalibrary.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList: ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>(), ButtonDetailClickListener, ButtonEditClickListener{
    class BookViewHolder(var view:BookListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater,R.layout.book_list_item, parent, false)
//        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.books = bookList[position]
        holder.view.listener = this
    }

    override fun onButtonDetailClick(v: View) {
        val action = BookListFragmentDirections.actionToBookDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: List<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onButtonEditClick(v: View) {
        val action = BookListFragmentDirections.actionToEditBookFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}