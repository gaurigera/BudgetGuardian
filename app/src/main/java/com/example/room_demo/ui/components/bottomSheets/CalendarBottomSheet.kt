package com.example.room_demo.ui.components.bottomSheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.room_demo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CalendarBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.date_picker_bottom_sheet, container, false)
    }
}