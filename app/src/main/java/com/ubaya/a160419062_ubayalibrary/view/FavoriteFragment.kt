package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private lateinit var viewModel : ListViewModel
    private val favListAdapter = BookFavAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
////        viewModel.fetchFav()
//
//        recFav.layoutManager = LinearLayoutManager(context)
////        recFav.adapter = bookListAdapter
//
//        observeViewModel()
//
//        refreshLayoutFav.setOnRefreshListener {
//            recFav.visibility = View.GONE
//            textErrorFav.visibility = View.GONE
//            progressListFav.visibility = View.VISIBLE
////            viewModel.fetchFav()
//            refreshLayoutFav.isRefreshing = false
//        }
//        (activity as AppCompatActivity).supportActionBar?.title = "Book Favorite Lists"
    }

    private fun observeViewModel() {
//        viewModel.bookFavLiveData.observe(viewLifecycleOwner, Observer {
//            bookListAdapter.updateBookList(it)
//        })
////        viewModel.bookFavLiveData.observe(viewLifecycleOwner) {
////            bookListAdapter.updateBookList(it)
////        }
//        viewModel.bookFavLoadErrorLD.observe(viewLifecycleOwner) {
//            textErrorFav.visibility = if (it) View.VISIBLE else View.GONE
//        }
//        viewModel.loadingFavLD.observe(viewLifecycleOwner) {
//            if(it) {
//                recFav.visibility = View.GONE
//                progressListFav.visibility = View.VISIBLE
//            } else {
//                recFav.visibility = View.VISIBLE
//                progressListFav.visibility = View.GONE
//            }
//        }
    }
}