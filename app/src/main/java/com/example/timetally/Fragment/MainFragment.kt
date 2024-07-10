package com.example.timetally.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.R
import com.example.timetally.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainFragment : Fragment() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private var selectedDate: Calendar = Calendar.getInstance()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        binding.fabBtn.setOnClickListener {
            val dialogFragment = CustomDialogFragment()
            dialogFragment.show(parentFragmentManager, "CustomDialogFragment")
        }
        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnAttendanceList.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayoutID, EmployeeListFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                updateDate()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.FrameLayoutID, EmployeeListFragment())
                    .addToBackStack(null)
                    .commit()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun updateDate() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.btnSelectDate.text = sdf.format(selectedDate.time)
    }
}



