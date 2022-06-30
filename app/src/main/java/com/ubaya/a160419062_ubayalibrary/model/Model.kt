package com.ubaya.a160419062_ubayalibrary.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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

@Entity(tableName = "favorite")
data class Favorite(
    @ColumnInfo(name = "accountId")
    var accountId: String?,
    @ColumnInfo(name = "bookId")
    var booktId: String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}

@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = false)
    var idAccount:String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "nrp")
    var nrp: String?,
    @ColumnInfo(name = "email")
    var email: String?,
    @ColumnInfo(name = "fakultas")
    var fakultas: String?,
    @ColumnInfo(name = "profilUrl")
    var profilUrl: String?,
)