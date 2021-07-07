package com.antonchuraev.becomebetter.views.toolbar

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.antonchuraev.becomebetter.R
import com.antonchuraev.becomebetter.base.BasePresenter
import com.antonchuraev.becomebetter.databinding.ViewCustomToolbarBinding
import com.antonchuraev.becomebetter.helpers.extensions.show
import com.antonchuraev.becomebetter.views.CustomView
import com.historic.app.global.navigation.FlowRouter
import ru.terrakok.cicerone.Router


class CustomToolbar @JvmOverloads constructor(context: Context , attrs: AttributeSet? = null , defStyleAttr: Int = 0) : CustomView<ViewCustomToolbarBinding>(context , attrs , defStyleAttr) {

	fun show() {
		this.isVisible = true
	}

	fun defaultBackButton(presenter: Router?) {
		setLeftButton(R.drawable.ic_arrow_back) { presenter?.exit() }
	}

	fun setLeftButton(@DrawableRes drawableRes: Int , clickListener: (() -> Unit)? = null) = binding.leftImage.apply {
		show()
		setImageDrawable(ContextCompat.getDrawable(context , drawableRes))
		setOnClickListener { clickListener?.invoke() }
	}


	fun setTittle(text: String?) {
		binding.tittle.text = text
	}

	fun clearData() {
		this.isVisible = false
		binding.tittle.text = ""
	}

	override fun getLayoutRes(): Int = R.layout.view_custom_toolbar
}