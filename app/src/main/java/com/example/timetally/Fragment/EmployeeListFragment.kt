package com.example.timetally.Fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timetally.ViewModel.EmployeeViewModel
import com.example.timetally.Adapter.EmployeeAdapter
import com.example.timetally.Adapter.PresenceAdapter
import com.example.timetally.R
import com.example.timetally.databinding.FragmentEmployeeListBinding
import java.util.Date
import java.util.Locale

private const val TAG = "EmployeeListFragment"

class EmployeeListFragment : Fragment() {
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var presenceAdapter: PresenceAdapter
    private lateinit var binding: FragmentEmployeeListBinding

    private var selectedDate: String = getCurrentDate()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        // Get selected date from arguments and update if available
        selectedDate = arguments?.getString("selected_date") ?: getCurrentDate()

        // Initialize adapters after updating selectedDate
        employeeAdapter = EmployeeAdapter(
            employeeViewModel.getEmployeeDao(), selectedDate) { employee, isChecked ->
            employeeViewModel.updateEmployeePresence(employee, selectedDate)
            Log.d(TAG, "onViewCreated: date: $selectedDate")
        }


        presenceAdapter = PresenceAdapter(selectedDate)

        // Setup RecyclerViews
        binding.employeeRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.employeeRecyclerView.adapter = employeeAdapter

        binding.recyclerViewEmployeePresence.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewEmployeePresence.adapter = presenceAdapter

        // Observe employee data and update adapter
        employeeViewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
            employees?.let { employeeAdapter.setEmployees(it) }
        })

        // Observe present employees by selected date
        employeeViewModel.getPresentEmployeesByDate(selectedDate)
            .observe(viewLifecycleOwner, Observer { presentEmployees ->
                presentEmployees?.let { presenceAdapter.setPresenceEmployees(it) }
                Log.d(TAG, "onViewCreated: Selected Date: $selectedDate")
            })

        // Button click listener
        binding.okButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayoutID, MainFragment())
                .commit()
        }

        // Observe present employees
        employeeViewModel.presentEmployees.observe(
            viewLifecycleOwner,
            Observer { presenceEmployees ->
                presenceEmployees?.let { presenceAdapter.setPresenceEmployees(it) }
            })
    }
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}

