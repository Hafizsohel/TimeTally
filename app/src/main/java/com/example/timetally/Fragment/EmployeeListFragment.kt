package com.example.timetally.Fragment

import android.icu.text.SimpleDateFormat
import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.timetally.ViewModel.EmployeeViewModel
    import com.example.timetally.Adapter.EmployeeAdapter
    import com.example.timetally.Adapter.PresenceAdapter
    import com.example.timetally.R
    import com.example.timetally.databinding.FragmentEmployeeListBinding
import java.util.Date
import java.util.Locale

class EmployeeListFragment : Fragment() {
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var presenceAdapter: PresenceAdapter

    private lateinit var binding:FragmentEmployeeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmployeeListBinding.inflate(layoutInflater)
        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        employeeAdapter = EmployeeAdapter(employeeViewModel.getEmployeeDao()) { employee, isChecked ->
            employeeViewModel.updateEmployeePresence(employee)
        }
        presenceAdapter = PresenceAdapter()

        binding.employeeRecyclerView.adapter = employeeAdapter
        binding.employeeRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewEmployeePresence.adapter = presenceAdapter
        binding.recyclerViewEmployeePresence.layoutManager = LinearLayoutManager(context)

        employeeViewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
            employees?.let { employeeAdapter.setEmployees(it) }
        })

        // Observe employeesByDate LiveData
       /* employeeViewModel.employeesByDate.observe(viewLifecycleOwner, Observer { employees ->
            employees?.let { employeeAdapter.setEmployees(it) }
        })*/


        binding.okButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.FrameLayoutID, MainFragment())
                .commit()
        }

        employeeViewModel.presentEmployees.observe(viewLifecycleOwner, Observer { presenceEmployees ->
            presenceEmployees?.let { presenceAdapter.setPresenceEmployees(it) }
        })



       /* val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        employeeViewModel.getEmployeesByDate(currentDate)
*/

        return binding.root
    }
}
