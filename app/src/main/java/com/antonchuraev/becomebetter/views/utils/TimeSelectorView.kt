package com.antonchuraev.becomebetter.views.utils

import android.content.Context
import android.util.AttributeSet
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.databinding.ViewTimeSelectorBinding
import com.antonchuraev.becomebetter.views.CustomView
import kotlin.properties.Delegates


class TimeSelectorView @JvmOverloads constructor(
		context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) :
		CustomView<ViewTimeSelectorBinding>(context , attrs , defStyleAttr) {



	public enum class Type( val startFrom:Int,val values: Map<Float , Int>?) {
		DURATION(
			0, mapOf(
				0F to R.string.forever , 1F to R.string.one_day , 2F to R.string.three_days ,
				3F to R.string.one_week , 4F to R.string.one_month , 5F to R.string.one_year
					 )
					 ) ,
		PRIORITY(
			 1, mapOf(
				0F to R.string.small_prority , 1F to R.string.standart_prority ,
				2F to R.string.higherst_prority
					 )
					 ),
		PROGRESS( 0 , null )

		;

		companion object {
			fun findByAttrs(attrReference: Int) =
				values().find { it.ordinal == attrReference } ?: throw java.lang.Exception(
					"нету такого стиля"
																								  )
		}

	}

	var changeListener: ((Float) -> Unit)? = null

	var style by Delegates.observable(Type.DURATION){_,_,new->
		setStyleViews(new)
	}

	private fun setStyleViews(style: Type) {
		binding.slider.apply {
			valueTo = if (style != Type.PROGRESS){
				style.values!!.size!!.toFloat() - 1F
			} else{
				100F
			}
			value = style.startFrom.toFloat()
			binding.selectedSize.text = generateTextForSlider(style , value)
		}
	}

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
			setLabelFormatter {
				changeListener?.invoke(it)
				generateTextForSlider(style , it).apply { binding.selectedSize.text = this }
			}
		}
	}

	fun getValue() = binding.slider.value

	fun setValue(value:Float){
		binding.slider.apply {
			this.value = value
			binding.selectedSize.text = generateTextForSlider(style , value)
		}
	}

	fun setMaxValue(value:Float){
		binding.slider.apply {
			valueTo = value
			binding.selectedSize.text = generateTextForSlider(style , value)
		}
	}

	private fun generateTextForSlider(styleValue: Type , value:Float): String {
		if (styleValue == Type.PROGRESS) return value.toInt().toString()
		return context.getString(styleValue.values!![value] ?: throw Exception("value больше допустимых значений") )
	}

	override fun getLayoutRes() = R.layout.view_time_selector
}