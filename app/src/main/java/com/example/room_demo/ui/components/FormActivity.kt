package com.example.room_demo.ui.components

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.room_demo.databinding.DetailsFormBinding
import com.example.room_demo.ui.components.bottomSheets.CalendarBottomSheet

class FormActivity : AppCompatActivity() {
    private lateinit var binding: DetailsFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.dateDayMonthButton.setOnClickListener {
            val calendarBottomSheet = CalendarBottomSheet()
            calendarBottomSheet.show(supportFragmentManager, calendarBottomSheet.tag)
        }
        binding.submitButton.setOnClickListener {
            // TODO("insert item through the repository")
        }
    }
}