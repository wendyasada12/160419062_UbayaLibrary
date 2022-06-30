package com.ubaya.a160419062_ubayalibrary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Profile::class), version = 1 )
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile private var instance:UserDB?=null
        // volatile -> data instance dapat otomatis diakses/dibaca oleh thread lain ketika dibutuhkan
        private val LOCK= Any()

        //Membuat database
        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
                UserDB::class.java, "userdb").build()

        // Memastikan bahwa object tododb adalah sigleton
        operator fun invoke(context: Context){
            if(instance!=null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}