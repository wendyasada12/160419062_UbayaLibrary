package com.ubaya.a160419062_ubayalibrary.model

import androidx.room.*

@Dao
interface BookDao {
    //Book
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(vararg book: Book)

    @Query("SELECT * FROM books")
    suspend fun selectAllBooks(): List<Book>

    @Query("SELECT * FROM books where id= :id")
    suspend fun selectBooks(id:String): Book

    @Delete
    suspend fun deleteBooks(book: Book)

}