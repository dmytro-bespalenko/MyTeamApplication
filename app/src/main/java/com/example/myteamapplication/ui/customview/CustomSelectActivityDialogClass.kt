package com.example.myteamapplication.ui.customview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.myteamapplication.R


class CustomSelectActivityDialogClass : DialogFragment(), View.OnClickListener {

    private lateinit var imageCategory: ImageView

    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton
    private lateinit var createCustomLayoutStep: CustomSelectFilterView
    private lateinit var createCustomLayoutDistance: CustomSelectFilterView
    private lateinit var customLayout: LinearLayout

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
        customLayout = view.findViewById(R.id.custom_layout)

        createCustomLayoutStep = createCustomLayout(view, R.drawable.sneakers, "STEP")
        createCustomLayoutDistance = createCustomLayout(view, R.drawable.navigation, "KM")
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
            saveButton -> Toast.makeText(requireContext(), "SAVE", Toast.LENGTH_SHORT).show()
            createCustomLayoutStep -> createCustomLayoutStep.setBackGround(R.drawable.button_radius)
            createCustomLayoutDistance -> createCustomLayoutDistance.setBackGround(R.drawable.button_radius)
        }

    }

    private fun createCustomLayout(v: View, id: Int, name: String): CustomSelectFilterView {

        val layout: View =
            LayoutInflater.from(v.context)
                .inflate(R.layout.custom_select_item, customLayout, false)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )
        layout.layoutParams = params

        customLayout.addView(layout)

        val customSelectFilterView = CustomSelectFilterView(v.context)
        customSelectFilterView.setCategoryImage(id)
        customSelectFilterView.setText(name)

        customLayout.addView(customSelectFilterView)

        return customSelectFilterView
    }

    private fun setupClickListeners(view: View) {
        cancelButton = view.findViewById(R.id.button_activity_cancel)
        saveButton = view.findViewById(R.id.button_activity_save)

        imageCategory = view.findViewById(R.id.image_category_select)
        createCustomLayoutStep.setOnClickListener(this)
        createCustomLayoutDistance.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)

    }

}
