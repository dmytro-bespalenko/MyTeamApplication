package com.example.myteamapplication.ui.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class RadioIndicatorButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CheckableIndicatorButton(context, attrs, defStyleAttr) {

    override fun performClick(): Boolean {
        if (isChecked) return false
        return super.performClick()
    }
}