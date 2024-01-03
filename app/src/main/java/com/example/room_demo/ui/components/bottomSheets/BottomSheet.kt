package com.example.room_demo.ui.components.bottomSheets

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.room_demo.R
import com.example.room_demo.ui.components.main_activity.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheet : BottomSheetDialogFragment() {
    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_dialog, container, false)
    }

    private val activityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
            }
        }

    private fun openRecentImages() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*" // Restrict to image files
        activityResult.launch(Intent(intent))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scanButton = view.findViewById<ImageButton>(R.id.scan_button)
        scanButton.setOnClickListener {
            if (!hasPermissions()) {
                ActivityCompat.requestPermissions(
                    requireActivity(), CAMERAX_PERMISSIONS, 0
                )
            } else {
                val activity = requireActivity() as MainActivity
                activity.openCamera()
                dismiss()
            }
        }

        val uploadButton = view.findViewById<ImageButton>(R.id.upload_button)
        uploadButton.setOnClickListener {
            openRecentImages()
        }

        val manualFillButton = view.findViewById<ImageButton>(R.id.manual_fill_button)
        manualFillButton.setOnClickListener {
            val activity = requireActivity() as MainActivity
            activity.openFillForm()
            dismiss()
        }
    }

    private fun hasPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}