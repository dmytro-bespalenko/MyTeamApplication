package com.example.myteamapplication.ui.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.myteamapplication.R

class CustomTimeFrameDialogClass : DialogFragment(), View.OnClickListener {

    private lateinit var customTodayFilterView: CustomSelectFilterView
    private lateinit var customWeekFilterView: CustomSelectFilterView
    private lateinit var customMonthFilterView: CustomSelectFilterView
    private lateinit var onClickDialogListener: OnClickDialogListener

    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton
    private lateinit var customDistance: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.custom_time_frame_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners(view)

        customTodayFilterView.setText("TODAY")
        customTodayFilterView.setCategoryImage(R.drawable.calendarfirst)

        customWeekFilterView.setText("7 DAYS")
        customWeekFilterView.setCategoryImage(R.drawable.calendarseven)

        customMonthFilterView.setText("MONTH")
        customMonthFilterView.setCategoryImage(R.drawable.calendarpage)

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onClick(v: View?) {

        when (v) {
            cancelButton -> dismiss()
            saveButton -> Toast.makeText(requireContext(), "Save", Toast.LENGTH_SHORT).show()
            customMonthFilterView -> {
                customMonthFilterView.setBackGround(R.drawable.button_radius)
            }
            customWeekFilterView -> {
                customWeekFilterView.setBackGround(R.drawable.button_radius)
            }
            customTodayFilterView -> {
                customTodayFilterView.setBackGround(R.drawable.button_radius)
            }

        }

    }


    private fun setupClickListeners(view: View) {
        customTodayFilterView = view.findViewById(R.id.left_time_button)
        customWeekFilterView = view.findViewById(R.id.middle_time_button)
        customMonthFilterView = view.findViewById(R.id.right_time_button)
        cancelButton = view.findViewById(R.id.button_time_frame_cancel)
        saveButton = view.findViewById(R.id.button_time_frame_save)


        saveButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)

        customTodayFilterView.setOnClickListener(this)
        customWeekFilterView.setOnClickListener(this)
        customMonthFilterView.setOnClickListener(this)

    }

    interface OnClickDialogListener {
        fun onDialogSaveClick()

    }

}