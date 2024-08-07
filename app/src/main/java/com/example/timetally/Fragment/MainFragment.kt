/*
package com.example.timetally.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.R
import com.example.timetally.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val TAG = "MainFragment"
class MainFragment : Fragment() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private var selectedDate: Calendar = Calendar.getInstance()
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        binding.fabBtn.setOnClickListener {
            val dialogFragment = CustomDialogFragment()
            dialogFragment.show(parentFragmentManager, "CustomDialogFragment")
        }
        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }
        binding.btnAttendanceList.setOnClickListener {
            val fragment = EmployeeListFragment().apply {
                arguments = Bundle().apply {
                    putString("selected_date", getSelectedDateString())
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayoutID, EmployeeStatusFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            requireContext(),
            R.style.DatePickerTheme,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                updateDate()
                Log.d(TAG, "selectedDate: ${selectedDate.time}")

                parentFragmentManager.beginTransaction()
                    .replace(R.id.FrameLayoutID, EmployeeListFragment())
                    .addToBackStack(null)
                    .commit()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        // Disable future dates
        datePicker.datePicker.maxDate = System.currentTimeMillis()

        // Disable previous dates before 6 days
        selectedDate.add(Calendar.DAY_OF_YEAR, -6)
        datePicker.datePicker.minDate = selectedDate.timeInMillis

        datePicker.show()
        datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Green))
        datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Green))
    }

    private fun updateDate() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.btnSelectDate.text = sdf.format(selectedDate.time)
    }
    private fun getSelectedDateString(): String {
        val myFormat = "yyyy-MM-dd" // Format suitable for database
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(selectedDate.time)
    }
}


*/

package com.example.timetally.Fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.R
import com.example.timetally.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val TAG = "MainFragment"
class MainFragment : Fragment() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private var selectedDate: Calendar = Calendar.getInstance()
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
        binding.fabBtn.setOnClickListener {
            val dialogFragment = CustomDialogFragment()
            dialogFragment.show(parentFragmentManager, "CustomDialogFragment")
        }
        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }

        binding.btnAttendanceList.setOnClickListener {
            val fragment = EmployeeListFragment().apply {
                arguments = Bundle().apply {
                    putString("selected_date", getSelectedDateString())
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayoutID, EmployeeStatusFragment())
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            requireContext(),
            R.style.DatePickerTheme,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                selectedDate.set(year, month, dayOfMonth)
                updateDate()
                Log.d(TAG, "selectedDate: ${selectedDate.time}")

                parentFragmentManager.beginTransaction()
                    .replace(R.id.FrameLayoutID, EmployeeListFragment().apply {
                        arguments = Bundle().apply {
                            putString("selected_date", getSelectedDateString())
                        }
                    })
                    .addToBackStack(null)
                    .commit()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        // Disable future dates
        datePicker.datePicker.maxDate = System.currentTimeMillis()

        // Disable previous dates before 6 days
        selectedDate.add(Calendar.DAY_OF_YEAR, -6)
        datePicker.datePicker.minDate = selectedDate.timeInMillis

        datePicker.show()
        datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Green))
        datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(requireContext(), R.color.Green))
    }

    private fun updateDate() {
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.btnSelectDate.text = sdf.format(selectedDate.time)
    }
    private fun getSelectedDateString(): String {
        val myFormat = "yyyy-MM-dd" // Format suitable for database
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(selectedDate.time)
    }
}
