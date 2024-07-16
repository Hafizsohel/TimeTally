package com.example.timetally.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.Data.Employee
import com.example.timetally.databinding.FragmentCustomDialogBinding

class CustomDialogFragment : DialogFragment() {
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var binding: FragmentCustomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCustomDialogBinding.inflate(layoutInflater)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        binding.buttonSubmit.setOnClickListener {
            val employeeName = binding.fullEmployeeName.text.toString()
            if (employeeName.isNotEmpty()) {
                val employee = Employee(name = employeeName, isPresence = false)
                employeeViewModel.addEmployee(employee)
                Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_LONG).show()
                dismiss()
            } else {
                Toast.makeText(requireContext(), "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}


