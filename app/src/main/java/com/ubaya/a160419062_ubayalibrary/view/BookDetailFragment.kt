package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.util.loadImage
import com.ubaya.a160419062_ubayalibrary.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val bookID = BookDetailFragmentArgs.fromBundle(requireArguments()).bookID
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(bookID)

            observeViewModel()

            buttonFav.setOnClickListener {
                viewModel.updateFav(bookID)
            }
            buttonLike.setOnClickListener {
                viewModel.updateLike(bookID)
            }
            buttonWish.setOnClickListener {
                viewModel.updatewish(bookID)
            }
            buttonBlackList.setOnClickListener {
                viewModel.updateBlack(bookID)
            }
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Book Detail"
    }

    private fun observeViewModel() {
        viewModel.bookLD.observe(viewLifecycleOwner){
            val book = viewModel.bookLD.value
            book?.let {
                textBookName.setText(it.name)
                textAuthor.setText(it.author)
                textDesc.setText(it.desc)
                textCategory.setText(it.category)
                textPages.setText(it.Pages)
                textLanguage.setText(it.Language)
                textDateRelease.setText(it.date)
                textPublisher.setText(it.publisher)
                imageBookDetail.loadImage(book.image, progressBookPhotoDetail)
            }
        }
        viewModel.fav.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context, "Book add to favorite", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Book remove from favorite", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.like.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context, "Book add to like", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Book remove from like", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.wish.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context, "Book add to wishlist", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Book remove from wishlist", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.black.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(context, "Book add to blacklist", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, "Book remove from blacklist", Toast.LENGTH_SHORT).show()
            }
        }
    }
}