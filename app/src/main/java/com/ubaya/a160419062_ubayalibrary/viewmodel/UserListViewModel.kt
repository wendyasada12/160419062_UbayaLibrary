package com.ubaya.a160419062_ubayalibrary.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.RequestQueue
import com.ubaya.a160419062_ubayalibrary.model.Book
import com.ubaya.a160419062_ubayalibrary.model.BookDB
import com.ubaya.a160419062_ubayalibrary.model.Profile
import com.ubaya.a160419062_ubayalibrary.model.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val userLiveData = MutableLiveData<Profile>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    var books :ArrayList<Book> = ArrayList()
    val TAG = "volleyTag"

    private var queue: RequestQueue? = null
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        userLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                UserDB::class.java, "userdb"
            ).build()

            userLiveData.value = db.userDao().selectAllProfile()
        }
    }

    fun addProfile(list:List<Profile>){
        launch {
            Log.d("Input Profile",list.toString())
            val db = Room.databaseBuilder(
                getApplication(),
                UserDB::class.java, "userdb"
            ).build()
            db.userDao().insertAllProfile(*list.toTypedArray())
        }
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}