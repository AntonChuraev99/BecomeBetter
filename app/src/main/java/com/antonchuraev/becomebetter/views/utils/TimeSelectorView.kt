package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewTimeSelectorBinding
import com.antonchuraev.becomebetter.views.CustomView


class TimeSelectorView @JvmOverloads constructor(
		context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) :
		CustomView<ViewTimeSelectorBinding>(context , attrs , defStyleAttr) {

	override fun getLayoutRes() = R.layout.view_time_selector

	public enum class Type( val startFrom:Int,val values: Map<Float , Int>) {
		GOAL_DURATION(
			0, mapOf(
				0F to R.string.forever , 1F to R.string.one_day , 2F to R.string.three_days ,
				3F to R.string.one_week , 4F to R.string.one_month , 5F to R.string.one_year
					 )
					 ) ,
		GOAL_PRIORITY(
			 1, mapOf(
				0F to R.string.small_prority , 1F to R.string.standart_prority ,
				2F to R.string.higherst_prority
					 )
					 ),
		PROGRESS( 0 , mapOf()
		)

		;

		companion object {
			fun findByAttrs(attrReference: Int) =
				values().find { it.ordinal == attrReference } ?: throw java.lang.Exception(
					"нету такого стиля"
																								  )
		}

	}

	var style = Type.GOAL_DURATION

	init {
         context.theme.obtainStyledAttributes(
			attrs , R.styleable.TimeSelectorView , 0 , 0
                                                        ).apply {
			try {
                style = Type.findByAttrs(getInteger(R.styleable.TimeSelectorView_type , 0))
			} finally {
				recycle()
			}
		}

		binding.slider.apply {
			if (style != Type.PROGRESS){
				valueTo = style.values.size.toFloat() - 1F
				value = style.startFrom.toFloat()
				binding.selectedSize.text = generateTextForSlider(style	 , value)
				setLabelFormatter {
					generateTextForSlider(style , it).apply { binding.selectedSize.text = this }
				}
			}
			else{
				valueTo = 100F
				value = style.startFrom.toFloat()
				binding.selectedSize.text = generateTextForSlider(style , value)
				setLabelFormatter {
					generateTextForSlider(style , it).apply { binding.selectedSize.text = this }
				}
			}


		}
	}

	fun getValue() = binding.slider.value

	fun setValue(value:Float){
		binding.slider.apply {
			this.value = value
			binding.selectedSize.text = generateTextForSlider(style , value)
			setLabelFormatter {
				generateTextForSlider(style , it).apply { binding.selectedSize.text = this }
			}
		}
	}

	private fun generateTextForSlider(styleValue: Type , value:Float): String {
		if (styleValue == Type.PROGRESS) return value.toInt().toString()
		return context.getString(styleValue.values[value] ?: throw Exception("value больше допустимых значений") )
	}
}