package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419062_ubayalibrary.model.Book
import com.ubaya.a160419062_ubayalibrary.model.BookDB
import com.ubaya.a160419062_ubayalibrary.model.BookDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val bookLD = MutableLiveData<Book>()
    val booksLoadError= MutableLiveData<Boolean>()
    val loadingLD= MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    val reviewsLD= MutableLiveData<List<Review>>()
    val reviewsLoadError= MutableLiveData<Boolean>()
    val reviewsloadingLD= MutableLiveData<Boolean>()
    val REVIEWTAG= "volleyTag"
    private var reviewsQueue: RequestQueue?= null

    var fav = MutableLiveData<Boolean>()
    var like = MutableLiveData<Boolean>()
    var wish = MutableLiveData<Boolean>()
    var black = MutableLiveData<Boolean>()

    private val job= Job()

    fun fetch(bookID: String) {
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

    fun addBooks(list: List<Book>){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                BookDatabase::class.java, "bookdatabase"
            ).build()
            db.bookDao().insertBooks(*list.toTypedArray())
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
        reviewsQueue?.cancelAll(REVIEWTAG)
    }

    override val coroutineContext: CoroutineContext
        get()= job  + Dispatchers.Main
}