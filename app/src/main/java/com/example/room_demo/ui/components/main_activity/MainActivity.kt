package com.example.room_demo.ui.components.main_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room_demo.BudgetGuardianApplication
import com.example.room_demo.data.data_classes.Category
import com.example.room_demo.databinding.ActivityMainBinding
import com.example.room_demo.ui.components.bottomSheets.BottomSheet
import com.example.room_demo.ui.components.camera.CameraActivity
import com.example.room_demo.ui.components.detail_form.FormActivity
import com.example.room_demo.ui.components.dialogs.CategoryDialog
import com.example.room_demo.ui.components.dialogs.CategoryDialogListener
import com.example.room_demo.ui.components.main_activity.recyclerViewUtil.CategoryAdapter

//BudgetGuardian
class MainActivity : AppCompatActivity(), CategoryDialogListener {
    private val categoryAdapter by lazy { CategoryAdapter() }
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val categoryRepo = (application as BudgetGuardianApplication).getCategoryRepo()
        val categoryItemRepo = (application as BudgetGuardianApplication).getCategoryItemRepo()
        val mainViewModelFactory = MainViewModelFactory(
            categoryItemRepo, categoryRepo
        )
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainActivityViewModel::class.java]

        val plusFab = viewBinding.plusFab
        plusFab.setOnClickListener {
            val bottomSheetFragment = BottomSheet()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        viewBinding.categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewBinding.addCategory.setOnClickListener {
            CategoryDialog().show(supportFragmentManager, "category_dialog_tag")
        }
    }

    fun openCamera() {
        val cameraIntent = Intent(this@MainActivity, CameraActivity::class.java)
        startActivity(cameraIntent)
    }

    fun openFillForm() {
        val formIntent = Intent(this@MainActivity, FormActivity::class.java)
        startActivity(formIntent)
    }

    override fun dialogResult(categoryName: String, spendLimit: String) {
        viewModel.addCategory(
            Category(
                0,
                categoryName,
                0.0,
                spendLimit.toDouble()
            )
        )
    }
}