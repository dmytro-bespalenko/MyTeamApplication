package com.example.myteamapplication.ui.customview

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.Checkable
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.view.children
import com.example.myteamapplication.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
open class CheckableIndicatorButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : IndicatorButton(context, attrs, defStyleAttr), Checkable {

    interface OnCheckedChangeListener {
        fun onCheckedChanged(view: View, isChecked: Boolean)
    }

    private val listeners = mutableListOf<OnCheckedChangeListener>()

    private var checked: Boolean = false

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.IndicatorButton, 0, 0).use {
            isChecked =
                it.getBoolean(R.styleable.CheckableIndicatorButton_android_checked, isChecked)
        }

        background = ContextCompat.getDrawable(context, R.drawable.checkable_background_selector)
        label.setTextColor(ContextCompat.getColorStateList(context, R.color.black))
    }

    fun addOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
        listeners.add(onCheckedChangeListener)
    }

    fun removeOnCheckChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
        listeners.remove(onCheckedChangeListener)
    }

    override fun isChecked() = checked


    override fun toggle() {
        isChecked = !checked
    }

    override fun setChecked(checked: Boolean) {

        if (this.checked != checked) {
            this.checked = checked
            children.filter { it is Checkable }.forEach { (it as Checkable).isChecked = checked }
            listeners.forEach { it.onCheckedChanged(this, this.checked) }
            refreshDrawableState()
        }

    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }


    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            View.mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }

}