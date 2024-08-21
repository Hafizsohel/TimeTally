package com.example.timetally.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timetally.Adapter.AbsentEmployeeAdapter
import com.example.timetally.Adapter.PresenceAdapter
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.databinding.FragmentEmployeeStatusBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val TAG = "EmployeeStatusFragment"
class EmployeeStatusFragment : Fragment() {

    private val employeeViewModel: EmployeeViewModel by viewModels()
    private lateinit var presenceAdapter: PresenceAdapter
    private lateinit var absentAdapter: AbsentEmployeeAdapter
    private lateinit var binding: FragmentEmployeeStatusBinding

    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEmployeeStatusBinding.inflate(layoutInflater)

        selectedDate = arguments?.getString("selected_date")
        Log.d(TAG, "Selected date in Fragment: $selectedDate")

        presenceAdapter = PresenceAdapter(selectedDate)
        absentAdapter = AbsentEmployeeAdapter()


        // Display the current date in the TextView
        val currentDate = getCurrentDateString()
        Log.d(TAG, "Current date: $currentDate")

        // Set the current date to the TextView
        binding.scrumDate.text = "Scrum Date: $currentDate"

        Log.d(TAG, "Selected date in Scrum Date: $selectedDate")


        binding.presentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = presenceAdapter
        }

        binding.absentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = absentAdapter
        }

        // Observe data from the ViewModel
        employeeViewModel.presentEmployees.observe(viewLifecycleOwner) { employees ->
            Log.d(TAG, "Present employees: $employees")
            presenceAdapter.setPresenceEmployees(employees)
            updateRecyclerViewVisibility()
        }
        binding.toolbarStatus.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        employeeViewModel.absentEmployees.observe(viewLifecycleOwner) { employees ->
            absentAdapter.setAbsentEmployees(employees)
            updateRecyclerViewVisibility()
        }

        binding.btnShare.setOnClickListener {
            shareEmployeeStatus()
        }
        return binding.root
    }

    private fun updateRecyclerViewVisibility() {
        val hasPresentEmployees = presenceAdapter.itemCount > 0
        val hasAbsentEmployees = absentAdapter.itemCount > 0

        if (hasPresentEmployees) {
            binding.presentRecyclerView.visibility = View.VISIBLE
        } else {
            binding.presentRecyclerView.visibility = View.GONE
        }

        if (hasAbsentEmployees) {
            binding.absentRecyclerView.visibility = View.VISIBLE
        } else {
            binding.absentRecyclerView.visibility = View.GONE
        }
    }
    private fun shareEmployeeStatus() {
        val presentEmployees = presenceAdapter.getEmployees()
        val absentEmployees = absentAdapter.getEmployees()

        if (presentEmployees.isEmpty() && absentEmployees.isEmpty()) {
            Toast.makeText(context, "No employee data to share", Toast.LENGTH_SHORT).show()
            return
        }

        val presentNames = presentEmployees.joinToString("\n") { it.name }
        val absentNames = absentEmployees.joinToString("\n") { it.name }

        val shareText = StringBuilder().apply {
            if(getCurrentDateString().isNotEmpty()){
                append("**Scrum Date:**\n${getCurrentDateString()}\n\n")
            }
            if (presentNames.isNotEmpty()) {
                append("**Present Employees:**\n$presentNames\n\n")
            }
            if (absentNames.isNotEmpty()) {
                append("**Absent Employees:**\n$absentNames")
            }
        }.toString()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share via")
        startActivity(shareIntent)
    }
    private fun getCurrentDateString(): String {
        val myFormat = "d-MMM-yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(Calendar.getInstance().time)
    }
}
