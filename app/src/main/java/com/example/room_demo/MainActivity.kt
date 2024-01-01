package com.example.room_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_demo.databinding.ActivityMainBinding
import com.example.room_demo.ui.components.bottomSheets.BottomSheet
import com.example.room_demo.ui.components.FormActivity
import com.example.room_demo.ui.components.camera.CameraActivity
import com.example.room_demo.ui.recyclerViewUtil.CategoryAdapter

//BudgetGuardian
class MainActivity : AppCompatActivity() {
    private val adapter by lazy { CategoryAdapter() }
    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
//        val donut_view = findViewById<DonutProgressView>(R.id.donut_view)
//        val section1 = DonutSection(
//            name = "section_1",
//            color = Color.parseColor("#FB1D32"),
//            amount = 1f
//        )
//        donut_view.submitData(listOf(section1, section2))
        val plusFab = viewBinding.plusFab
        plusFab.setOnClickListener {
            val bottomSheetFragment = BottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
        viewBinding.categoryRecyclerView.adapter = adapter
        viewBinding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun openCamera() {
        val cameraIntent = Intent(this@MainActivity, CameraActivity::class.java)
        startActivity(cameraIntent)
    }

    fun openFillForm() {
        val formIntent = Intent(this@MainActivity, FormActivity::class.java)
        startActivity(formIntent)
    }
}