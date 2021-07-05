package com.antonchuraev.becomebetter.helpers.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible


fun Context?.toast(text:String){
    Toast.makeText(this, "$text", Toast.LENGTH_SHORT).show()
}

fun View.gone(){
    this.isVisible = false
}

fun View.show(){
    this.isVisible = true
}