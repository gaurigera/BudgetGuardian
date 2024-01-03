package com.example.room_demo.ui.components.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.room_demo.databinding.AddCategoryDialogBinding

interface CategoryDialogListener {
    fun dialogResult(categoryName: String, spendLimit: String)
}

class CategoryDialog : DialogFragment() {

    private var _binding: AddCategoryDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var listener: CategoryDialogListener
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = AddCategoryDialogBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddCategoryDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSaveListener()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as CategoryDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement MyDialogListener")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpSaveListener() {
        binding.saveCategoryButton.setOnClickListener {
            if (binding.addCategoryName.text.isNullOrBlank() || binding.addSpendLimit.text.isNullOrBlank())
                dismiss()
            else {
                val categoryName = binding.addCategoryName.text.toString()
                val spendLimit = binding.addSpendLimit.text.toString()
                listener.dialogResult(categoryName, spendLimit)
                dismiss()
            }
        }
    }
}