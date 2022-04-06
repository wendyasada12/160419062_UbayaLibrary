package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.viewmodel.FavListViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private lateinit var viewModel : FavListViewModel
    private val bookListAdapter = BookFavAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FavListViewModel::class.java)
        viewModel.refresh()

        recFav.layoutManager = LinearLayoutManager(context)
        recFav.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutFav.setOnRefreshListener {
            recFav.visibility = View.GONE
            textErrorFav.visibility = View.GONE
            progressListFav.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFav.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.bookFavLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookFavLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorFav.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingFavLD.observe(viewLifecycleOwner) {
            if(it) {
                recFav.visibility = View.GONE
                progressListFav.visibility = View.VISIBLE
            } else {
                recFav.visibility = View.VISIBLE
                progressListFav.visibility = View.GONE
            }
        }
    }
}