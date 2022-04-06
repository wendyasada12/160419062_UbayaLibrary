package com.ubaya.a160419062_ubayalibrary.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.viewmodel.BlackListViewModel
import kotlinx.android.synthetic.main.fragment_blacklist.*

class BlacklistFragment : Fragment() {
    private lateinit var viewModel : BlackListViewModel
    private val bookListAdapter = BookBlacklistAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blacklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BlackListViewModel::class.java)
        viewModel.refresh()

        recBlacklist.layoutManager = LinearLayoutManager(context)
        recBlacklist.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutBlacklist.setOnRefreshListener {
            recBlacklist.visibility = View.GONE
            textErrorBlacklist.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutBlacklist.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.bookBlackLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.bookBlackLoadErrorLD.observe(viewLifecycleOwner) {
            textErrorBlacklist.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingBlackLD.observe(viewLifecycleOwner) {
            if(it) {
                recBlacklist.visibility = View.GONE
                progressBlacklistList.visibility = View.VISIBLE

            } else {
                recBlacklist.visibility = View.VISIBLE
                progressBlacklistList.visibility = View.GONE
            }
        }
    }
}