package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) :AndroidViewModel(application), CoroutineScope {
    val bookLiveData = MutableLiveData<List<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh(){
        loadingLD.value =true
        bookLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                BookDB::class.java, "newtododb"
            ).build()

            bookLiveData.value = db.bookDao().selectAllBooks()
        }

//        bookLiveData.value
//
//        bookLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/native/160419062/ANMP/book.php"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<ArrayList<Book>>(){}.type
//                val result = Gson().fromJson<ArrayList<Book>>(it, sType)
//                bookLiveData.value = result
//                loadingLD.value = false
//                Log.d("showVolley", it)
//            },
//            {
//                loadingLD.value = false
//                bookLoadErrorLD.value = true
//                Log.d("errorVolley", it.toString())
//            }
//        ).apply {
//            tag = TAG
//        }
//        queue?.add(stringRequest)
    }

//    fun clearTask(book: Book) {
//        launch {
//            val db = Room.databaseBuilder(
//                getApplication(),
//                BookDB::class.java, "newbookdb"
//            ).build()
//            db.bookDao().deleteBooks(book)
//
//            bookLiveData.value = db.bookDao().selectAllBooks()
//        }
//    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}