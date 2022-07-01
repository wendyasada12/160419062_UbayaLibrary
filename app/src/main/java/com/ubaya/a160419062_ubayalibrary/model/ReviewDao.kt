package com.ubaya.a160419062_ubayalibrary.model

import androidx.room.*

@Dao
interface ReviewDao {

    // Review
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(vararg review: Review)

    @Query("SELECT * FROM review where bookId= :bookId")
    suspend fun selectReviewsBooks(bookId:String): List<Review>
}