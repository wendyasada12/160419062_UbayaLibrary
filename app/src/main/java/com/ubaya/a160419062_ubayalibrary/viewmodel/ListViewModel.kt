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
import com.ubaya.a160419062_ubayalibrary.model.Account
import com.ubaya.a160419062_ubayalibrary.model.Book
import com.ubaya.a160419062_ubayalibrary.model.BookDB
import com.ubaya.a160419062_ubayalibrary.model.Favorite
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application) :AndroidViewModel(application), CoroutineScope {
    val bookLiveData = MutableLiveData<List<Book>>()
    val bookFavUserLiveData = MutableLiveData<List<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    var books :ArrayList<Book> = ArrayList()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    val bookFavLiveData = MutableLiveData<List<Favorite>>()
    val bookFavLoadErrorLD = MutableLiveData<Boolean>()
    val loadingFavLD = MutableLiveData<Boolean>()
    val FavTAG = "volleyTag"
    private var favqueue: RequestQueue? = null

    val accountsLD= MutableLiveData<Account>()
    val accountsLoadError= MutableLiveData<Boolean>()
    val accountsloadingLD= MutableLiveData<Boolean>()
    val ACCOUNTTAG= "volleyTag"
    private var accountQueue: RequestQueue?= null

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        bookLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                BookDB::class.java, "bookdb"
            ).build()

            bookLiveData.value = db.bookDao().selectAllBooks()
        }
    }
    fun refreshFav() {
        bookFavLoadErrorLD.value= false
        loadingFavLD.value= true

        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                BookDB::class.java, "bookdb"
            ).build()
            bookFavLiveData.value =  db.bookDao().selectFavorite()
        }
        loadingFavLD.value=false
    }

    fun addBooks(list: List<Book>){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                BookDB::class.java, "bookdb"
            ).build()
            db.bookDao().insertBooks(*list.toTypedArray())
        }
    }

    fun fetchFav(){
        loadingFavLD.value =true
        bookFavLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                BookDB::class.java, "bookdb"
            ).build()

            bookFavUserLiveData.value = db.bookDao().selectAllFavByUserId()
        }
    }

    fun fetchAccount(accountId:String){
        accountsLoadError.value= false

        launch {
            val db= Room.databaseBuilder(
                getApplication(),
                BookDB::class.java, "bookdb"
            ).build()
            accountsLD.value =  db.bookDao().selectAccount(accountId)
            Log.d("AccountDetailViewModel:" , accountsLD.value.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
        favqueue?.cancelAll(FavTAG)
        accountQueue?.cancelAll(ACCOUNTTAG)
    }

}