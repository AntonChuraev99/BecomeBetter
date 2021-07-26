package com.antonchuraev.becomebetter.helpers.extensions

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible

fun View.setMatchMatch() {
    this.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
}

fun View.setMatchWrap() {
    this.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
}

fun View.setWrapWrap() {
    this.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
}


fun Context?.toast(text:String){
    Toast.makeText(this, "$text", Toast.LENGTH_SHORT).show()
}

fun View.gone(){
    this.isVisible = false
}

fun View.show(){
    this.isVisible = true
}