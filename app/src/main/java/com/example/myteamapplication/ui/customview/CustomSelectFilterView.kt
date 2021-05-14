package com.example.myteamapplication.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.example.myteamapplication.R

class CustomSelectFilterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs), View.OnClickListener {


//    private val categoryRadioButton: Button
    private var categoryTextView: TextView
    private var image: ImageView
    private var layoutImage: LinearLayout



    init {
        inflate(context, R.layout.custom_select_item, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomSelectFilterView)

        image = findViewById(R.id.image_category_select)
        layoutImage = findViewById(R.id.layout_image)
        categoryTextView = findViewById(R.id.text_category_select)
        categoryTextView.text = attributes.getText(R.styleable.CustomSelectFilterView_category_name)
        attributes.recycle()
    }


    fun setCategoryImage(id: Int) {
        image.setBackgroundResource(id)
    }

    fun setBackGround(id: Int) {
        layoutImage.setBackgroundResource(id)

    }

    fun setText(str: String) {
        categoryTextView.text = str
    }

    override fun onClick(v: View?) {
//        categoryRadioButton.setBackgroundResource(R.drawable.background_radio)
    }


}
