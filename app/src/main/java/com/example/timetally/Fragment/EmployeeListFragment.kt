package com.example.timetally.Fragment

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

/*class EmployeeListFragment : Fragment() {

        private lateinit var employeeViewModel: EmployeeViewModel
        private lateinit var employeeAdapter: EmployeeAdapter
        private lateinit var presenceAdapter: PresenceAdapter

        private var _binding: FragmentEmployeeListBinding? = null
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)

            employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

           *//* employeeAdapter = EmployeeAdapter(employeeViewModel.getEmployeeDao()) { employee ->
                employeeViewModel.addEmployee(employee)
            }*//*

            employeeAdapter = EmployeeAdapter(employeeViewModel.getEmployeeDao()) { employee ->
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
            employeeViewModel.presentEmployees.observe(viewLifecycleOwner, Observer { presentEmployees ->
                presentEmployees?.let { presenceAdapter.setPresenceEmployees(it) }
            })
            return binding.root
        }
    }*/


class EmployeeListFragment : Fragment() {
    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var presenceAdapter: PresenceAdapter

    private var _binding: FragmentEmployeeListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeListBinding.inflate(inflater, container, false)

        employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

        employeeAdapter = EmployeeAdapter(employeeViewModel.getEmployeeDao()) { employee ->
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

        employeeViewModel.presentEmployees.observe(viewLifecycleOwner, Observer { presentEmployees ->
            presentEmployees?.let { presenceAdapter.setPresenceEmployees(it) }
        })

        return binding.root
    }
}
