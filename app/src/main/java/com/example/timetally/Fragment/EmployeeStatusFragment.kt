package com.example.timetally.Fragment

import android.content.Intent
import android.os.Bundle
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
        presenceAdapter = PresenceAdapter(selectedDate)
        absentAdapter = AbsentEmployeeAdapter()

        binding.presentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = presenceAdapter
        }

        binding.absentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = absentAdapter
        }

        employeeViewModel.presentEmployees.observe(viewLifecycleOwner) { employees ->
            presenceAdapter.setPresenceEmployees(employees)
            updateRecyclerViewVisibility()
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
}
