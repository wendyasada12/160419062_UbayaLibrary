package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.RequestQueue
import com.ubaya.a160419062_ubayalibrary.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReviewDetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val reviewsLD= MutableLiveData<List<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null

    val bookLD = MutableLiveData<Book>()
    val booksLoadError= MutableLiveData<Boolean>()
    val loadingLD= MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    val profileLD= MutableLiveData<Profile>()
    val profileLoadError= MutableLiveData<Boolean>()
    val profileloadingLD= MutableLiveData<Boolean>()
    val PROFILETAG= "volleyTag"
    private var profileQueue: RequestQueue?= null

    private val job= Job()

    fun addDataReview(list: List<Review>){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                ReviewDB::class.java, "reviewdb"
            ).build()
            db.reviewDao().insertReviews(*list.toTypedArray())
        }
    }

    fun fetchBook(bookID: String) {
        booksLoadError.value= false
        loadingLD.value= true

        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                BookDatabase::class.java, "bookdatabase"
            ).build()
            bookLD.value= db.bookDao().selectBooks(bookID)
            Log.d("bookld", bookLD.value.toString())
        }
        loadingLD.value= false
    }

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

    fun fetchReviews(booksId:String){
        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                ReviewDB::class.java, "reviewdb"
            ).build()
            reviewsLD.value= db.reviewDao().selectReviewsBooks(booksId)
            Log.d("review", reviewsLD.value.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
        profileQueue?.cancelAll(PROFILETAG)
        reviewsQueue?.cancelAll(REVIEWTAG)
    }

    override val coroutineContext: CoroutineContext
        get()= job  + Dispatchers.Main
}