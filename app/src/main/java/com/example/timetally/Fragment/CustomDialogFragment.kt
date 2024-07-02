package com.example.timetally.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.ViewModel.EmployeeViewModel
import com.example.timetally.Data.Employee
import com.example.timetally.databinding.FragmentCustomDialogBinding

private const val TAG = "CustomDialogFragment"
class CustomDialogFragment : DialogFragment() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        binding.buttonSubmit.setOnClickListener {
            val employeeName = binding.fullEmployeeName.text.toString()
            if (employeeName.isNotEmpty()) {
                val employee = Employee(name = employeeName)
                employeeViewModel.addEmployee(employee)
                Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_LONG).show()
                dismiss()
                Log.d("CustomDialogFragment", "Employee added: $employeeName")
            } else {
                Toast.makeText(requireContext(), "Please enter a name", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonCancel.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


