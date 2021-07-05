package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.dataClasses.Goal
import com.antonchuraev.becomebetter.databinding.ViewGoalInListBinding
import com.antonchuraev.becomebetter.helpers.extensions.toast
import com.antonchuraev.becomebetter.views.CustomView


class TimeSelectorView @JvmOverloads constructor(context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0 ): CustomView<ViewGoalInListBinding>(context, attrs, defStyleAttr) {

    override fun getLayoutRes() = R.layout.view_time_selector




}