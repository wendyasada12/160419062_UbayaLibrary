package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.RequestQueue
import com.ubaya.a160419062_ubayalibrary.model.Profile
import com.ubaya.a160419062_ubayalibrary.model.Review
import com.ubaya.a160419062_ubayalibrary.model.ReviewDB
import com.ubaya.a160419062_ubayalibrary.model.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val profileLD= MutableLiveData<Profile>()
    val profileLoadError= MutableLiveData<Boolean>()
    val profileloadingLD= MutableLiveData<Boolean>()
    val PROFILETAG= "volleyTag"
    private var profileQueue: RequestQueue?= null

    private val job= Job()

    val reviewsLD= MutableLiveData<List<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null



    fun fetchProfile(profileId:String){
        profileLoadError.value= false

        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                UserDB::class.java, "userdb"
            ).build()
            profileLD.value =  db.userDao().selectProfile(profileId)
            Log.d("ProfileDetailViewModel:" , profileLD.value.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        profileQueue?.cancelAll(PROFILETAG)
        reviewsQueue?.cancelAll(REVIEWTAG)
    }

    override val coroutineContext: CoroutineContext
        get()= job  + Dispatchers.Main


}