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


class BookFavAdapter(val favBookList: ArrayList<Book>) : RecyclerView.Adapter<BookFavAdapter.FavViewHolder>(), ButtonDetailClickListener, ButtonEditClickListener{
    class FavViewHolder(var view: BookListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFavAdapter.FavViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater,R.layout.book_list_item, parent, false)
        return FavViewHolder(view)
    }
    override fun onBindViewHolder(holder: BookFavAdapter.FavViewHolder, position: Int) {
        holder.view.books = favBookList[position]
        holder.view.listener = this
//        val book = bookList[position]
//        with (holder.view){
//            textID.text = book.id
//            textBName.text = book.name
//            textAuthorName.text = book.author
//            buttonDetail.setOnClickListener {
//                val action = book.id?.let { id ->
//                    FavoriteFragmentDirections.actionFavToBookDetail(id)
//
//                }
//                if (action != null)
//                {
//                    Navigation.findNavController(it).navigate(action)
//                }
//            }
//            imageBookPhoto.loadImage(book.image, progressLoadingBookPhoto)
//        }
    }

    override fun getItemCount() = favBookList.size

    fun updateFavBookList(newBookList: List<Book>){
        favBookList.clear()
        favBookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = FavoriteFragmentDirections.actionFavToBookDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonEditClick(v: View) {
        TODO("Not yet implemented")
    }
}