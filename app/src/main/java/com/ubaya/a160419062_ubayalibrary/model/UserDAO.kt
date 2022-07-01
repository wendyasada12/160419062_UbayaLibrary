package com.ubaya.a160419062_ubayalibrary.model

import androidx.room.*

@Dao
interface UserDao {
    // Profile
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProfile(vararg profile: Profile)

    @Query("SELECT * FROM profile WHERE idProfile=:id")
    suspend fun selectProfile(id:String): Profile

    @Query("SELECT * FROM profile" )
    suspend fun selectAllProfile(): Profile

    @Update
    suspend fun updateProfile(profile: Profile)
}