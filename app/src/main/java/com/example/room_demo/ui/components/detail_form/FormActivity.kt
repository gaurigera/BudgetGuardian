package com.example.room_demo.ui.components.detail_form

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.room_demo.BudgetGuardianApplication
import com.example.room_demo.databinding.DetailsFormBinding
import com.example.room_demo.ui.components.bottomSheets.CalendarBottomSheet
import kotlinx.coroutines.launch
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

interface BottomSheetListener {
    fun onDataReceived(data: Long)
}

class FormActivity : AppCompatActivity(), BottomSheetListener {
    private lateinit var binding: DetailsFormBinding
    private lateinit var viewModel: FormViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val formViewModelFactory = FormViewModelFactory(
            (application as BudgetGuardianApplication).categoryItemRepository,
            (application as BudgetGuardianApplication).categoryRepository
        )
        viewModel = ViewModelProvider(this, formViewModelFactory)[FormViewModel::class.java]
        viewModel.date.observe(this) { data ->
            binding.dateDayMonth.text = data
        }
        binding.categorySpinner.setSelection(viewModel.selectedSpinnerPosition)
    }

    override fun onResume() {
        super.onResume()
        binding.categorySpinner.setSelection(0, true)
        binding.dateDayMonthButton.setOnClickListener {
            val calendarBottomSheet = CalendarBottomSheet()
            calendarBottomSheet.show(supportFragmentManager, calendarBottomSheet.tag)
        }
        binding.submitButton.setOnClickListener {

        }
        lifecycleScope.launch {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                viewModel.categoryNames.collect { categoryNames ->
                    val adapter = ArrayAdapter(
                        this@FormActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        categoryNames
                    )
                    binding.categorySpinner.adapter = adapter
                }
        }

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.selectedSpinnerPosition = position
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.selectedSpinnerPosition = 0
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDataReceived(data: Long) {
        val sdf = SimpleDateFormat("yyyy-MM-DD", Locale.ENGLISH)
        val date = Date(data)
        println(date)
        val temp = sdf.format(date)
        println(temp)
        viewModel.date.value = temp
        viewModel.date.postValue(temp)
    }
}