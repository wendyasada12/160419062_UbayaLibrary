package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.viewmodel.LikeListViewModel
import kotlinx.android.synthetic.main.fragment_like.*

class LikeFragment : Fragment() {
    private lateinit var viewModel : LikeListViewModel
    private val bookListAdapter = BookLikeAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(LikeListViewModel::class.java)
        viewModel.refresh()

        recLike.layoutManager = LinearLayoutManager(context)
        recLike.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutLike.setOnRefreshListener {
            recLike.visibility = View.GONE
            textErrorLike.visibility = View.GONE
            progressLikeList.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutLike.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.bookLikeLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookLikeLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorLike.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLikeLD.observe(viewLifecycleOwner) {
            if(it) {
                recLike.visibility = View.GONE
                progressLikeList.visibility = View.VISIBLE
            } else {
                recLike.visibility = View.VISIBLE
                progressLikeList.visibility = View.GONE
            }
        }
    }
}