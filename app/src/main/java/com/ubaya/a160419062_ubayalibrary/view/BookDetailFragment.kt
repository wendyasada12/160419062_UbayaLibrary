package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.databinding.FragmentBookDetailBinding
import com.ubaya.a160419062_ubayalibrary.util.loadImage
import com.ubaya.a160419062_ubayalibrary.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookDetailFragment : Fragment(), ButtonAddReviewClickListener{
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_book_detail, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        var bookId=""
        if(arguments != null){
            bookId= BookDetailFragmentArgs.fromBundle(requireArguments()).bookID
        }
        viewModel.fetch(bookId)

//        arguments?.let {
//            val bookID = BookDetailFragmentArgs.fromBundle(requireArguments()).bookID
//            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//            viewModel.fetch(bookID)
//
//
//
//            buttonFav.setOnClickListener {
//                viewModel.updateFav(bookID)
//            }
//            buttonLike.setOnClickListener {
//                viewModel.updateLike(bookID)
//            }
//            buttonWish.setOnClickListener {
//                viewModel.updatewish(bookID)
//            }
//            buttonBlackList.setOnClickListener {
//                viewModel.updateBlack(bookID)
//            }
//        }
//
        (activity as AppCompatActivity).supportActionBar?.title = "Book Detail"

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bookLD.observe(viewLifecycleOwner){

            var book = it
            dataBinding.books = book

            imageBookDetail.loadImage(book.image, progressBookPhotoDetail)
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

    override fun onButtonAddReview(v: View) {
        val action = BookDetailFragmentDirections.actionAddReviewFragment(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}