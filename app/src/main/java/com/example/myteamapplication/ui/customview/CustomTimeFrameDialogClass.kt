package com.example.myteamapplication.ui.customview

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.marginTop
import androidx.fragment.app.DialogFragment
import com.example.myteamapplication.R

class CustomTimeFrameDialogClass : DialogFragment(), View.OnClickListener {

    private lateinit var imageCategory: ImageView

    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton
    private lateinit var customLayout: LinearLayout
    private lateinit var createCustomLayoutDay: CustomSelectFilterView
    private lateinit var createCustomLayoutWeek: CustomSelectFilterView
    private lateinit var createCustomLayoutMonth: CustomSelectFilterView

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
        customLayout = view.findViewById(R.id.custom_time_frame_layout)

        createCustomLayoutDay = createCustomLayout(view, R.drawable.calendarfirst, "TODAY")
        createCustomLayoutWeek = createCustomLayout(view, R.drawable.calendarseven, "7 DAYS")
        createCustomLayoutMonth = createCustomLayout(view, R.drawable.calendarpage, "MONTH")
        setupClickListeners(view)


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

            createCustomLayoutDay -> createCustomLayoutDay.setBackGround(R.drawable.button_radius)
            createCustomLayoutWeek -> createCustomLayoutWeek.setBackGround(R.drawable.button_radius)
            createCustomLayoutMonth -> createCustomLayoutMonth.setBackGround(R.drawable.button_radius)

        }

    }


    private fun setupClickListeners(view: View) {

        cancelButton = view.findViewById(R.id.button_time_frame_cancel)
        saveButton = view.findViewById(R.id.button_time_frame_save)
        imageCategory = view.findViewById(R.id.image_category_select)

        createCustomLayoutDay.setOnClickListener(this)
        createCustomLayoutWeek.setOnClickListener(this)
        createCustomLayoutMonth.setOnClickListener(this)

        customLayout.setOnClickListener(this)
        imageCategory.setOnClickListener(this)
        saveButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)


    }


    private fun createCustomLayout(v: View, id: Int, name: String): CustomSelectFilterView {

        val layout: View =
            LayoutInflater.from(v.context)
                .inflate(R.layout.custom_select_item, customLayout, false)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )

        params.setMargins(0, 60, 40, 0)

        customLayout.layoutParams = params

        customLayout.addView(layout)

        val customSelectFilterView = CustomSelectFilterView(v.context)

        customSelectFilterView.setCategoryImage(id)
        customSelectFilterView.setText(name)
        customLayout.addView(customSelectFilterView)

        return customSelectFilterView
    }

    interface OnClickDialogListener {
        fun onDialogSaveClick()

    }

}