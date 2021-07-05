package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewTimeSelectorBinding
import com.antonchuraev.becomebetter.views.CustomView
import java.util.*


class TimeSelectorView @JvmOverloads constructor(context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0 ): CustomView<ViewTimeSelectorBinding>(context, attrs, defStyleAttr) {

    override fun getLayoutRes() = R.layout.view_time_selector


    init {

        binding.slider.setLabelFormatter{
            generateTextForSlider(it).apply { binding.selectedSize.text = this }
        }
    }

    private fun generateTextForSlider(sliderValue: Float): String {
         return when (sliderValue){
             0F->context.getString(R.string.forever)
             1F->context.getString(R.string.one_day)
             2F->context.getString(R.string.three_days)
             3F->context.getString(R.string.one_week)
             4F->context.getString(R.string.one_month)
             5F->context.getString(R.string.one_year)
             else -> throw Exception("Значение не определено")
         }
    }
}