package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewTimeSelectorBinding
import com.antonchuraev.becomebetter.views.CustomView
import java.util.*


class TimeSelectorView @JvmOverloads constructor(
		context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) :
		CustomView<ViewTimeSelectorBinding>(context , attrs , defStyleAttr) {

	override fun getLayoutRes() = R.layout.view_time_selector

	public enum class Type(val referenceToAttr: Int , val startFrom:Int,val values: Map<Float , Int>) {
		GOAL_DURATION(
			0 , 0, mapOf(
				0F to R.string.forever , 1F to R.string.one_day , 2F to R.string.three_days ,
				3F to R.string.one_week , 4F to R.string.one_month , 5F to R.string.one_year
					 )
					 ) ,
		GOAL_PRIORITY(
			1 , 1, mapOf(
				0F to R.string.small_prority , 1F to R.string.standart_prority ,
				2F to R.string.higherst_prority
					 )
					 );

		companion object {
			fun findByAttrs(attrReference: Int) =
				values().find { it.referenceToAttr == attrReference } ?: throw java.lang.Exception(
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
			valueTo = style.values.size.toFloat() - 1F
			value = style.startFrom.toFloat()
			binding.selectedSize.text = generateTextForSlider(style.referenceToAttr , value)
			setLabelFormatter {
				generateTextForSlider(style.referenceToAttr , it).apply { binding.selectedSize.text = this }
			}
		}
	}

	fun setValue(value:Float){
		binding.slider.apply {
			this.value = value
			binding.selectedSize.text = generateTextForSlider(style.referenceToAttr , value)
			setLabelFormatter {
				generateTextForSlider(style.referenceToAttr , it).apply { binding.selectedSize.text = this }
			}
		}
	}

	private fun generateTextForSlider(styleValue: Int , sliderValue: Float): String {
		return context.getString(Type.findByAttrs(styleValue).values[sliderValue] ?: throw Exception("value больше допустимых значений") )
	}
}