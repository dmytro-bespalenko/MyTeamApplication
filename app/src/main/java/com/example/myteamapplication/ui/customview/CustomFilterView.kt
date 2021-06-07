package com.example.myteamapplication.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myteamapplication.R

class CustomFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var filterTextView: TextView


    init {
        inflate(context, R.layout.custom_filter, this)
        val imageViewLeft: ImageView = findViewById(R.id.image_step)
        filterTextView = findViewById(R.id.text_step)
        val imageArrowLeft: ImageView = findViewById(R.id.image_arrow)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomFilterView)
        imageViewLeft.setImageDrawable(attributes.getDrawable(R.styleable.CustomFilterView_image_left_step))
        filterTextView.text = attributes.getText(R.styleable.CustomFilterView_text_left)
        imageArrowLeft.setImageDrawable(attributes.getDrawable(R.styleable.CustomFilterView_arrow_left))
        attributes.recycle()
    }


    fun setTextView(text: String) {
        filterTextView.text = text

    }


}