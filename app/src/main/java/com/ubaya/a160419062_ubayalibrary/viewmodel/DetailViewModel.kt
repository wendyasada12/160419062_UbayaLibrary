package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419062_ubayalibrary.model.Book
import org.json.JSONObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val bookLD = MutableLiveData<Book>()
    val TAG = "detailtag"
    private var queue: RequestQueue? = null
    var fav = MutableLiveData<Boolean>()
    var like = MutableLiveData<Boolean>()
    var wish = MutableLiveData<Boolean>()
    var black = MutableLiveData<Boolean>()
    fun fetch(bookID: String?) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419062/ANMP/book.php?id=$bookID"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val result = Gson().fromJson<ArrayList<Book>>(it,sType)
                bookLD.value = result[0]
                Log.d("showbook", it)
            },
            {
                Log.d("errorbook", it.toString())
            }
        ).apply {
            tag = TAG
        }
        queue?.add(stringRequest)
    }

    fun updateFav(bookID: String?){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419062/ANMP/bookfav.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    fav.value = (obj.getString("message") == "add")
                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
                }
                Log.d("showbook", it)
            },
            {
                Log.d("errorbook", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = bookID.toString()
                return params
            }
        }
        queue?.add(stringRequest)
    }
    fun updateLike(bookID: String?){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419062/ANMP/booklike.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    like.value = (obj.getString("message") == "add")
                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
                }
                Log.d("showbook", it)
            },
            {
                Log.d("errorbook", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = bookID.toString()
                return params
            }
        }
        queue?.add(stringRequest)
    }
    fun updatewish(bookID: String?){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419062/ANMP/bookwishlist.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    wish.value = (obj.getString("message") == "add")
                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
                }
                Log.d("showbook", it)
            },
            {
                Log.d("errorbook", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = bookID.toString()
                return params
            }
        }
        queue?.add(stringRequest)
    }
    fun updateBlack(bookID: String?){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419062/ANMP/bookblacklist.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                //val sType = object : TypeToken<ArrayList<Book>>() {}.type
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    black.value = (obj.getString("message") == "add")
                    bookLD.value?.isFavorite = (obj.getString("message") == "add")
                }
                Log.d("showbook", it)
            },
            {
                Log.d("errorbook", it.toString())
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["id"] = bookID.toString()
                return params
            }
        }
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}