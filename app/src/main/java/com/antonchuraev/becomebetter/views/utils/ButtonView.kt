package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewButtonBinding
import com.antonchuraev.becomebetter.views.CustomView
import kotlin.properties.Delegates


class ButtonView @JvmOverloads constructor(
		context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) :
		CustomView<ViewButtonBinding>(context , attrs , defStyleAttr) {

	override fun getLayoutRes() = R.layout.view_button

	enum class Type(val referenceToAttr: Int , val backgroundColor: Int ,val textColor: Int) {
		DISABLE (0 , R.color.F0F0F5 , R.color.с9FA2B4) ,
		ACTIVE(1 , R.color.aston_martin , R.color.main_blue);

		companion object {
			fun findByAttrs(attrReference: Int) =
				values().find { it.referenceToAttr == attrReference } ?: throw java.lang.Exception(
					"нету такого стиля"
																								  )
		}

	}

	var style by Delegates.observable(Type.DISABLE){ _ , _ ,new ->
		updateStyle(new)
	}

	init {
		context.theme.obtainStyledAttributes(
			attrs , R.styleable.ButtonView , 0 , 0
											).apply {
			try {
				binding.button.text = getString(R.styleable.ButtonView_android_text)
				style = Type.findByAttrs(getInteger(R.styleable.ButtonView_buttonType , 0))
			} finally {
				recycle()
			}
		}
	}

	private fun updateStyle(type: Type) {
		binding.button.apply {
			backgroundTintList = ColorStateList.valueOf(context.getColor(type.backgroundColor))
			setTextColor(context.getColor(type.textColor))

		}
	}

}