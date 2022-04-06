package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419062_ubayalibrary.model.Book
import org.json.JSONArray
import org.json.JSONObject

class BlackListViewModel(application: Application) : AndroidViewModel(application) {
    val bookBlackLiveData = MutableLiveData<ArrayList<Book>>()
    val bookBlackLoadErrorLD = MutableLiveData<Boolean>()
    val loadingBlackLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        bookBlackLiveData.value

        bookBlackLoadErrorLD.value = false
        loadingBlackLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/ANMP/bookblacklistlist.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val sType = object : TypeToken<ArrayList<Book>>(){}.type
                    val result = Gson().fromJson<ArrayList<Book>>(obj.getString("data"), sType)
                    bookBlackLiveData.value = result
                    loadingBlackLD.value = false
                    Log.d("showVolley", it)
                }
            },
            {
                loadingBlackLD.value = false
                bookBlackLoadErrorLD.value = true
                Log.d("errorVolley", it.toString())
            }
        ).apply {
            tag = TAG
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}