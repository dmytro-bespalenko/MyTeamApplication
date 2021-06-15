package com.example.myteamapplication.ui.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.view.children
import com.example.myteamapplication.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
open class IndicatorButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttrs) {

    var icon: ImageView
    var indicator: ImageView
    var label: TextView

    init {

        LayoutInflater.from(context).inflate(R.layout.filter_button_layout, this, true)
        icon = findViewById(R.id.icon)
        indicator = findViewById(R.id.indicator)
        label = findViewById(R.id.label)

        context.theme.obtainStyledAttributes(attrs, R.styleable.IndicatorButton, 0, 0).use {
            isEnabled = it.getBoolean(R.styleable.IndicatorButton_android_enabled, isEnabled)
            icon.setImageDrawable(it.getDrawable(R.styleable.IndicatorButton_icon))
            label.text = it.getText(R.styleable.IndicatorButton_android_text)
        }

        background = ContextCompat.getDrawable(context, R.drawable.background_shape)
        label.setTextColor(ContextCompat.getColorStateList(context, R.color.black))

        isClickable = true
        isFocusable = true
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        children.forEach { it.isEnabled = enabled }
    }

    fun setCategoryImage(id: Int) {
        icon.setBackgroundResource(id)
    }

    fun setText(name: String) {
        label.text = name
    }

}