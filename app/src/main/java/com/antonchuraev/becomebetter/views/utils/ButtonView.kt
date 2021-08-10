package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewButtonBinding
import com.antonchuraev.becomebetter.views.CustomView
import java.nio.file.Files.delete
import kotlin.properties.Delegates


class ButtonView @JvmOverloads constructor(
		context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) :
		CustomView<ViewButtonBinding>(context , attrs , defStyleAttr) {

	override fun getLayoutRes() = R.layout.view_button

	enum class Type(val referenceToAttr: Int , val backgroundColor: Int ,val textRes:Int ,val textColor: Int , val isEnableToChange:Boolean = false) {
		DISABLE (0 , R.color.F0F0F5 , R.string.create ,  R.color.с9FA2B4 , isEnableToChange = true) ,
		ACTIVE(1 , R.color.aston_martin , R.string.create, R.color.main_blue , isEnableToChange = true),
		DELETE(2 , R.color.F0F0F5 , R.string.delete, R.color.error),
		SAVE(3 ,R.color.aston_martin , R.string.save, R.color.main_blue)
		;

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
			text = context.getString(type.textRes)
			backgroundTintList = ColorStateList.valueOf(context.getColor(type.backgroundColor))
			setTextColor(context.getColor(type.textColor))
		}
	}

	fun setActiveState(isEnabled:Boolean){
		if (style.isEnableToChange) style = if (isEnabled) Type.ACTIVE else Type.DISABLE
	}

	fun onClickListener(action:(()->Unit)?){
		binding.button.setOnClickListener {
			action?.invoke()
		}
	}
}