package com.example.room_demo.ui.components.bottomSheets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.room_demo.databinding.DatePickerBottomSheetBinding
import com.example.room_demo.ui.components.detail_form.BottomSheetListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
import java.util.Date

class CalendarBottomSheet : BottomSheetDialogFragment() {
    private lateinit var listener: BottomSheetListener
    private var _binding: DatePickerBottomSheetBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement BottomSheetListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DatePickerBottomSheetBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calenderView.setOnDateChangeListener { calView, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            calView.date = selectedDate.time.time
        }
        binding.saveButton.setOnClickListener {
            println(Date(binding.calenderView.date))
            listener.onDataReceived(binding.calenderView.date)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}