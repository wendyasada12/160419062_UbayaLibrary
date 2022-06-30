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

    //Favorite
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFav(vararg favorite: Favorite)

    @Query("SELECT * FROM books B " +
            "INNER JOIN favorite F ON F.bookId = B.id " +
            "INNER JOIN account A ON A.idAccount = F.accountId")
    suspend fun selectAllFavByUserId(): List<Book>

    @Query("SELECT * FROM favorite")
    suspend fun selectFavorite():List<Favorite>


    //Accounnt
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAccount(vararg accounts: Account)

    @Query("SELECT * FROM account WHERE idAccount=:id")
    suspend fun selectAccount(id:String): Account

    @Update
    suspend fun updateAccount(account: Account)
}