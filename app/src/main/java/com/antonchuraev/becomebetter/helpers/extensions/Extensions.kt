package com.antonchuraev.becomebetter.helpers.extensions

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
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

fun EditText.addSuffix(suffix: String) {
    val editText = this
    val formattedSuffix = " $suffix"
    var text = ""
    var isSuffixModified = false

    val setCursorPosition: () -> Unit =
        { Selection.setSelection(editableText, editableText.length - formattedSuffix.length) }

    val setEditText: () -> Unit = {
        editText.setText(text)
        setCursorPosition()
    }

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val newText = editable.toString()

            if (isSuffixModified) {
                // user tried to modify suffix
                isSuffixModified = false
                setEditText()
            } else if (text.isNotEmpty() && newText.length < text.length && !newText.contains(formattedSuffix)) {
                // user tried to delete suffix
                setEditText()
            } else if (!newText.contains(formattedSuffix)) {
                // new input, add suffix
                text = "$newText$formattedSuffix"
                setEditText()
            } else {
                text = newText
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            charSequence?.let {
                val textLengthWithoutSuffix = it.length - formattedSuffix.length
                if (it.isNotEmpty() && start > textLengthWithoutSuffix) {
                    isSuffixModified = true
                }
            }
        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}