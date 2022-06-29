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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val bookLD = MutableLiveData<List<Book>>()
    val booksLoadError= MutableLiveData<Boolean>()
    val loadingLD= MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    var fav = MutableLiveData<Boolean>()
    var like = MutableLiveData<Boolean>()
    var wish = MutableLiveData<Boolean>()
    var black = MutableLiveData<Boolean>()

    private val job= Job()

    fun fetch(bookID: String?) {
//        bookLD.value= false
//        loadingLD.value= true
//
//        launch {
//            val db= Room.databaseBuilder(
//                getApplication(),
//                BookDB::class.java, "bookdb"
//            ).build()
//            bookLD.value= db.bookDao().selectBooks(bookID)
//            Log.d("bookld", bookLD.value.toString())
//        }
//        loadingLD.value= false
    }

//    fun updateFav(bookID: String?){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/native/160419062/ANMP/bookfav.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST,
//            url,
//            {
//                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    fav.value = (obj.getString("message") == "add")
//                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
//                }
//                Log.d("showbook", it)
//            },
//            {
//                Log.d("errorbook", it.toString())
//            }
//        ){
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["id"] = bookID.toString()
//                return params
//            }
//        }
//        queue?.add(stringRequest)
//    }
//    fun updateLike(bookID: String?){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/native/160419062/ANMP/booklike.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST,
//            url,
//            {
//                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    like.value = (obj.getString("message") == "add")
//                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
//                }
//                Log.d("showbook", it)
//            },
//            {
//                Log.d("errorbook", it.toString())
//            }
//        ){
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["id"] = bookID.toString()
//                return params
//            }
//        }
//        queue?.add(stringRequest)
//    }
//    fun updatewish(bookID: String?){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/native/160419062/ANMP/bookwishlist.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST,
//            url,
//            {
//                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    wish.value = (obj.getString("message") == "add")
//                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
//                }
//                Log.d("showbook", it)
//            },
//            {
//                Log.d("errorbook", it.toString())
//            }
//        ){
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["id"] = bookID.toString()
//                return params
//            }
//        }
//        queue?.add(stringRequest)
//    }
//    fun updateBlack(bookID: String?){
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://ubaya.fun/native/160419062/ANMP/bookblacklist.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST,
//            url,
//            {
//                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
//                val obj = JSONObject(it)
//                if(obj.getString("result") == "OK") {
//                    black.value = (obj.getString("message") == "add")
//                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
//                }
//                Log.d("showbook", it)
//            },
//            {
//                Log.d("errorbook", it.toString())
//            }
//        ){
//            override fun getParams(): MutableMap<String, String> {
//                val params = HashMap<String, String>()
//                params["id"] = bookID.toString()
//                return params
//            }
//        }
//        queue?.add(stringRequest)
//    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override val coroutineContext: CoroutineContext
        get()= job  + Dispatchers.Main
}