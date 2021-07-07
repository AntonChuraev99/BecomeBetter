package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewButtonBinding
import com.antonchuraev.becomebetter.databinding.ViewTimeSelectorBinding
import com.antonchuraev.becomebetter.views.CustomView
import java.util.*


class ButtonView @JvmOverloads constructor(
		context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) :
		CustomView<ViewButtonBinding>(context , attrs , defStyleAttr) {

	override fun getLayoutRes() = R.layout.view_button

	private enum class Type(val referenceToAttr: Int , val startFrom:Int,val values: Map<Float , Int>) {
		;

		companion object {
			fun findByAttrs(attrReference: Int) =
				values().find { it.referenceToAttr == attrReference } ?: throw java.lang.Exception(
					"нету такого стиля"
																								  )
		}

	}


	init {


	}

}