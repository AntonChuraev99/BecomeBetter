package com.antonchuraev.becomebetter.helpers.extensions

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.antonchuraev.becomebetter.R

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

fun RecyclerView.add10DpDecorators(context:Context) {
    this.addItemDecoration(
        DividerItemDecoration(
        context, DividerItemDecoration.HORIZONTAL
    ).apply {
        setDrawable(
            ContextCompat.getDrawable(
                context, R.drawable.empty_divider_s_10
            )!!
        )
    })
    this.addItemDecoration(
        DividerItemDecoration(
        context, DividerItemDecoration.VERTICAL
    ).apply {
        setDrawable(
            ContextCompat.getDrawable(
                context,R.drawable.empty_divider_s_10
            )!!
        )
    })
}

/**
 * доавить декортаор выбранной ориентатции
 * @param orientation DividerItemDecoration.VERTICAL or DividerItemDecoration.HORIZONTAL
 */
fun RecyclerView.add10DpDecorator(context:Context?, orientation:Int) {
    context?.let {
        this.addItemDecoration(
            DividerItemDecoration(
            context,orientation
        ).apply {
            setDrawable(
                ContextCompat.getDrawable(
                    context,R.drawable.empty_divider_s_10
                )!!
            )
        })
    }

}