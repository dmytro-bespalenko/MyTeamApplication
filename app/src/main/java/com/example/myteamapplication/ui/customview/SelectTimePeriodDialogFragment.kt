package com.example.myteamapplication.ui.customview

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.myteamapplication.R


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectTimePeriodDialogFragment() : DialogFragment(),
    View.OnClickListener {


    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton
    private lateinit var dayButton: RadioIndicatorButton
    private lateinit var weekButton: RadioIndicatorButton
    private lateinit var monthButton: RadioIndicatorButton
    private lateinit var timePeriodAllFilters: ArrayList<String>
    private lateinit var radioGroup: ViewGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            timePeriodAllFilters = bundle.getStringArrayList("list")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.custom_activity_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup = view.findViewById(R.id.indicator_radio_group)

        val splitStrings: ArrayList<String> = separateString(timePeriodAllFilters)



        for (str in splitStrings) {
            when (str) {
                "Today" -> dayButton =
                    createCustomLayout(view, R.drawable.calendarfirst, str, this)
                "7 Days" -> weekButton =
                    createCustomLayout(view, R.drawable.calendarseven, str, this)
                "Month" -> monthButton =
                    createCustomLayout(view, R.drawable.calendarpage, str, this)
            }
            setupClickListeners(view)
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onClick(v: View?) {
        val intent = Intent()

        when (v) {
            cancelButton -> dismiss()
            saveButton ->
                when {
                dayButton.isChecked -> {
                    intent.putExtra("time", "Today")
                    targetFragment?.onActivityResult(
                        targetRequestCode,
                        Activity.RESULT_OK,
                        intent
                    )
                    dismiss()
                }
                weekButton.isChecked -> {
                    intent.putExtra("time", "7 Days")
                    targetFragment?.onActivityResult(
                        targetRequestCode,
                        Activity.RESULT_OK,
                        intent
                    )
                    dismiss()
                }
                monthButton.isChecked -> {
                    intent.putExtra("time", "Month")
                    targetFragment?.onActivityResult(
                        targetRequestCode,
                        Activity.RESULT_OK,
                        intent
                    )
                    dismiss()

                }
            }
        }
    }

    private fun separateString(list: ArrayList<String>): ArrayList<String> {
        val joinToString = list.joinToString(separator = ",")

        return ArrayList(joinToString.split(","))
    }

    private fun setupClickListeners(view: View) {
        cancelButton = view.findViewById(R.id.button_activity_cancel)
        saveButton = view.findViewById(R.id.button_activity_save)
        saveButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)

    }


    private fun createCustomLayout(
        v: View,
        id: Int,
        name: String,
        onClickListener: View.OnClickListener
    ): RadioIndicatorButton {

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )

        params.setMargins(0, 50, 10, 0)
        params.gravity = Gravity.CENTER_HORIZONTAL
        radioGroup.layoutParams = params
        val customSelectFilterView = RadioIndicatorButton(v.context)
        customSelectFilterView.setCategoryImage(id)
        customSelectFilterView.setText(name)
        radioGroup.addView(customSelectFilterView)
        customSelectFilterView.setOnClickListener(onClickListener)

        return customSelectFilterView
    }


}