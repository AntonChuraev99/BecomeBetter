package com.antonchuraev.becomebetter.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class CustomView<B : ViewDataBinding>(context: Context,
                                               attrs: AttributeSet? = null,
                                               defStyleAttr: Int = 0,
                                               var layout: Int) : FrameLayout(context, attrs, defStyleAttr) {

    protected var binding: B = DataBindingUtil.inflate( LayoutInflater.from(context) , layout , null , false)

    /*@get:LayoutRes
    protected abstract val layoutRes: Int*/


}