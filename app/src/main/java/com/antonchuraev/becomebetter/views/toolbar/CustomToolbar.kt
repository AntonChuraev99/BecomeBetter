package com.antonchuraev.becomebetter.views.toolbar

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.isVisible
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewCustomToolbarBinding
import com.antonchuraev.becomebetter.views.CustomView


class CustomToolbar @JvmOverloads constructor(context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0 ): CustomView<ViewCustomToolbarBinding>(context, attrs, defStyleAttr ) {

	fun setTittle(text:String?){
		binding.tittle.text = text
	}

	fun clearData() {
		this.isVisible = false
		binding.tittle.text = ""
	}
	override fun getLayoutRes(): Int = R.layout.view_custom_toolbar
}