package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.viewmodel.WishListViewModel
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishlistFragment : Fragment() {
    private lateinit var viewModel : WishListViewModel
    private val bookListAdapter = BookWishAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(WishListViewModel::class.java)
        viewModel.refresh()

        recWishlist.layoutManager = LinearLayoutManager(context)
        recWishlist.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutWish.setOnRefreshListener {
            recWishlist.visibility = View.GONE
            textErrorWishlist.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutWish.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.bookWishLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookWishLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorWishlist.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingWishLD.observe(viewLifecycleOwner) {
            if(it) {
                recWishlist.visibility = View.GONE
                progressWishlistList.visibility = View.VISIBLE
            } else {
                recWishlist.visibility = View.VISIBLE
                progressWishlistList.visibility = View.GONE
            }
        }
    }
}