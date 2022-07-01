package com.ubaya.a160419062_ubayalibrary.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419062_ubayalibrary.R
import com.ubaya.a160419062_ubayalibrary.databinding.FragmentProfileBinding
import com.ubaya.a160419062_ubayalibrary.model.Profile
import com.ubaya.a160419062_ubayalibrary.util.loadImage
import com.ubaya.a160419062_ubayalibrary.viewmodel.DetailViewModel
import com.ubaya.a160419062_ubayalibrary.viewmodel.UserListViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var dataBinding: FragmentProfileBinding

    companion object{
        val SHARED_PROFILE_ID="SHARED_PROFILE_ID"
        val SHARED_PROFILE_NAME="SHARED_PROFILE_NAME"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        dataBinding.listener = this

        viewModel= ViewModelProvider(this).get(UserListViewModel::class.java)
        var account1= Profile("1","Wendy", "160419062", "6", "https://i.pinimg.com/736x/a4/55/ec/a455ec1d2e2dfdc529b6dabb215c289c.jpg")

        val listProfile = listOf(account1)
        viewModel.addProfile(listProfile)

        //Retrieve the saved profile id
        var sharedId = context?.packageName
        var shared = context?.getSharedPreferences(sharedId, Context.MODE_PRIVATE)
        var playerId = shared?.getString(SHARED_PROFILE_ID, null)

        viewModel.refresh()

        observeViewModel()

        (activity as AppCompatActivity).supportActionBar?.title = "Profile Tab"

    }

    private fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner){
            var users = it
            dataBinding.profile = users

            imageProfile.loadImage(users.photoUrl, progressLoadUserPhoto)
        }
    }

}