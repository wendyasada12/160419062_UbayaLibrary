package com.ubaya.a160419062_ubayalibrary.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    var name:String?,
    var author:String?,
    @SerializedName("description")
    var desc:String?,
    var category:String?,
    @SerializedName("pages")
    var Pages:String?,
    @SerializedName("language")
    var Language:String?,
    var date:String?,
    var publisher:String?,
    var image:String?,
    var isFavorite:Boolean?,
    var isLike:Boolean?,
    var isWishlist:Boolean?,
    var isBlacklist:Boolean?,
)