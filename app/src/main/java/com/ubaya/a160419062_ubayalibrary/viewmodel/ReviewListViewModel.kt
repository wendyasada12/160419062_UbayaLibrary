package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419062_ubayalibrary.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReviewListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val reviewsLD= MutableLiveData<List<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null

    private var job = Job()

    fun refreshReview(booksId:String){
        reviewsLoadError.value= false
        reviewsloadingLD.value = true
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                ReviewDB::class.java, "reviewdb"
            ).build()

            reviewsLD.value = db.reviewDao().selectReviewsBooks(booksId)
        }
    }

    override fun onCleared() {
        reviewsQueue?.cancelAll(REVIEWTAG)
    }

    override val coroutineContext: CoroutineContext
        get()= job  + Dispatchers.Main
}