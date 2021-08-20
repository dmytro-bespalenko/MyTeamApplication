package com.example.myteamapplication.ui.customview

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.myteamapplication.R

const val ARG_DISTANCE_LIST = "list"
const val STEP = "Step"
const val KM = "Km"

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class SelectDistanceDialogFragment : DialogFragment(), View.OnClickListener {


    private lateinit var saveButton: AppCompatButton
    private lateinit var cancelButton: AppCompatButton
    private lateinit var radioGroup: ViewGroup
    private lateinit var distanceAllFilters: ArrayList<String>
    private lateinit var stepButton: RadioIndicatorButton
    private lateinit var kmButton: RadioIndicatorButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            distanceAllFilters = bundle.getStringArrayList("list")!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.custom_activity_dialog, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioGroup = view.findViewById(R.id.indicator_radio_group)
        val splitStrings: ArrayList<String> = separateString(distanceAllFilters)
        for (str in splitStrings) {
            when (str) {
                STEP -> stepButton = createCustomLayout(view, R.drawable.sneakers, str)
                KM -> kmButton = createCustomLayout(view, R.drawable.navigation, str)
            }
        }
        setupClickListeners(view)

    }

    private fun separateString(list: ArrayList<String>): ArrayList<String> {
        val joinToString = list.joinToString(separator = ",")
        return ArrayList(joinToString.split(","))
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View?) {
        val intent = Intent()
        when (v) {
            cancelButton -> dismiss()
            saveButton -> if (stepButton.isChecked) {
                intent.putExtra("step", STEP)
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    intent
                )
                dismiss()
            } else if (kmButton.isChecked) {
                intent.putExtra("step", KM)
                targetFragment?.onActivityResult(
                    targetRequestCode,
                    Activity.RESULT_OK,
                    intent
                )
                dismiss()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createCustomLayout(
        v: View,
        id: Int,
        name: String
    ): RadioIndicatorButton {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )
        params.setMargins(0, 50, 10, 0)
        params.gravity = Gravity.CENTER
        radioGroup.layoutParams = params

        val customSelectFilterView = RadioIndicatorButton(v.context)
        customSelectFilterView.setCategoryImage(id)

        customSelectFilterView.setText(name)
        radioGroup.addView(customSelectFilterView)
        customSelectFilterView.setOnClickListener(this)

        return customSelectFilterView
    }

    companion object {
        fun newInstance(list: ArrayList<String>): SelectDistanceDialogFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DISTANCE_LIST, list)
            }
            return SelectDistanceDialogFragment().apply {
                arguments = args
            }
        }

    }


    private fun setupClickListeners(view: View) {
        cancelButton = view.findViewById(R.id.button_activity_cancel)
        saveButton = view.findViewById(R.id.button_activity_save)
        cancelButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)

    }

}
