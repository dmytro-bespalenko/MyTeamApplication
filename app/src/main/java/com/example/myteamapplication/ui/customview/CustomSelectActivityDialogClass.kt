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

class CustomSelectActivityDialogClass : DialogFragment(), View.OnClickListener {

    private lateinit var customStepFilterView: CustomSelectFilterView
    private lateinit var customDistanceFilterView: CustomSelectFilterView
    private lateinit var customDistance: LinearLayout

    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton

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

        cancelButton = view.findViewById(R.id.button_activity_cancel)
        saveButton = view.findViewById(R.id.button_activity_save)
        customStepFilterView = view.findViewById(R.id.left_filter_button)
        customDistanceFilterView = view.findViewById(R.id.right_filter_button)


        customDistance = view.findViewById(R.id.layout_without_text)

        customDistance.setOnClickListener(this)

        cancelButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)

        customStepFilterView.setText("STEP")
        customStepFilterView.setCategoryImage(R.drawable.sneakers)
        customDistanceFilterView.setText("KM")
        customDistanceFilterView.setCategoryImage(R.drawable.navigation)

    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    override fun onClick(v: View?) {
        when (v) {
            cancelButton -> dismiss()
            saveButton -> Toast.makeText(requireContext(), "SAVE", Toast.LENGTH_SHORT).show()
            customDistance -> v.setBackgroundResource(R.drawable.button_radius)

        }

    }

}
