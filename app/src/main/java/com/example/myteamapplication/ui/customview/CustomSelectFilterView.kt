package com.example.myteamapplication.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myteamapplication.R

class CustomSelectFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val categoryImageView: ImageView
    private var categoryTextView: TextView


    init {
        inflate(context, R.layout.custom_select_item, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomSelectFilterView)

        categoryImageView = findViewById(R.id.image_category_select)
        categoryTextView = findViewById(R.id.text_category_select)
        categoryImageView.setImageDrawable(attributes.getDrawable(R.styleable.CustomSelectFilterView_category_image))
        categoryTextView.text = attributes.getText(R.styleable.CustomSelectFilterView_category_name)
        attributes.recycle()
    }


    fun setCategoryImage(id: Int) {
        categoryImageView.setBackgroundResource(id)
    }

    fun setBackGround(id: Int) {
        categoryImageView.setBackgroundResource(id)

    }

    fun setText(str: String) {
        categoryTextView.text = str
    }

}
