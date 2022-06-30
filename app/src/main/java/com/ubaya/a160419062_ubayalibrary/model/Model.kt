package com.ubaya.a160419062_ubayalibrary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = false)
    var id:String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "category")
    var category: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "author")
    var author: String?,
    @ColumnInfo(name = "pages")
    var pages: String?,
    @ColumnInfo(name = "language")
    var language: String?,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "date")
    var date: String?,
    @ColumnInfo(name = "publisher")
    var publisher: String?
)

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = false)
    var idProfile:String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "nrp")
    var nrp: String?,
    @ColumnInfo(name = "semester")
    var semester: String?,
    @ColumnInfo(name = "photoUrl")
    var photoUrl: String?,
)