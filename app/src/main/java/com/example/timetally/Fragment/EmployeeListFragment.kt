package com.example.timetally.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ViewModel.EmployeeViewModel
import com.example.timetally.Adapter.EmployeeAdapter
import com.example.timetally.databinding.FragmentEmployeeListBinding

class EmployeeListFragment : Fragment() {
    private lateinit var employeeViewModel: EmployeeViewModel
    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EmployeeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)

        adapter = EmployeeAdapter()
        binding.employeeRecyclerView.adapter = adapter
        binding.employeeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        employeeViewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
            employees?.let { adapter.setEmployees(it) }
        })

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
