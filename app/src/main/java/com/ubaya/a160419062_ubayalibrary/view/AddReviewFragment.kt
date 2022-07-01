package com.ubaya.a160419062_ubayalibrary.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.databinding.FragmentAddReviewBinding
import com.ubaya.a160419062_ubayalibrary.model.Review
import com.ubaya.a160419062_ubayalibrary.viewmodel.ReviewDetailViewModel
import kotlinx.android.synthetic.main.book_review_item.*
import kotlinx.android.synthetic.main.fragment_add_review.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddReviewFragment : Fragment(){
    private lateinit var viewModel:ReviewDetailViewModel
    private lateinit var dataBinding: FragmentAddReviewBinding

    companion object{
        val SHARED_PROFILE_ID="SHARED_PROFILE_ID"
        val SHARED_PROFILE_NAME="SHARED_PROFILE_NAME"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentAddReviewBinding>(inflater, R.layout.fragment_add_review, container, false)
        return dataBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        dataBinding.listener = this

        viewModel= ViewModelProvider(this).get(ReviewDetailViewModel::class.java)
        var bookId=""
        if(arguments != null){
            bookId= AddReviewFragmentArgs.fromBundle(requireArguments()).bookId
        }
        viewModel.fetchReviews(bookId)


        observeViewModel()

        buttonSaveCommentReview.setOnClickListener {
            var sharedId = context?.packageName
            var shared = context?.getSharedPreferences(sharedId, Context.MODE_PRIVATE)
            var userId = shared?.getString(ProfileFragment.SHARED_PROFILE_ID, null)
            var userName = shared?.getString(ProfileFragment.SHARED_PROFILE_NAME, null)

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formatted = current.format(formatter)

//            val tag = view.tag.toString().split(",")
//            val userid = userId
//            val username = userName
            val bookid = "0006"
            val userid = "1"
            val username = "Wendy"

            var review =
                Review(username, formatted.toString(), textEdittCommentReview.text.toString(), userid, bookId)
            viewModel.addDataReview(listOf(review))
            Toast.makeText(view.context, "review success added!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()

        }

    }
    private fun observeViewModel() {
//        viewModel.bookLD.observe(viewLifecycleOwner){
//
//            var book= it
//            dataBinding.book = book
//        }
//        viewModel.profileLD.observe(viewLifecycleOwner) {
//
//            var profil = it
//            dataBinding.profile = profil
//        }
    }

}