package com.antonchuraev.becomebetter.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class CustomView<B : ViewDataBinding>(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 1) : FrameLayout(context, attrs, defStyleAttr) {

    protected lateinit var binding: B

    init {
        initBinding()
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this.context),
            getLayoutRes(), this, true
        )
    }

    @LayoutRes protected abstract fun getLayoutRes(): Int

    fun loge(text:Any){
        Log.e("tag", "log:${text.toString()}")
    }
}