package com.ubaya.a160419062_ubayalibrary.view

import android.view.View
import android.widget.CompoundButton
import com.ubaya.a160419062_ubayalibrary.model.Book

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton,
                       isChecked:Boolean,
                       obj: Book
    )
}

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface ButtonEditClickListener{
    fun onButtonEditClick(v: View)
}

interface ButtonLikeClickListener{
    fun onButtonLikeClick(v: View)
}

interface ButtonFavClickListener{
    fun onButtonFavClick(v: View)
}

interface ButtonWishClickListener{
    fun onButtonWishlClick(v: View)
}

interface ButtonBlackClickListener{
    fun onButtonBlackClick(v: View)
}

interface ButtonAddReviewClickListener{
    fun onButtonAddReview(v: View)
}

interface ButtonCreateBookClickListener{
    fun onButtonCreateClick(v: View)
}

interface ButtonEditBookClick{
    fun onButtonEditClick(v: View)
}

interface RadioClick {
    fun onRadioClick(v:View, priority:Int, obj:Book)
}