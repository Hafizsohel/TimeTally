    /*
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
    */

 /*   package com.example.timetally.Fragment

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.ViewModel.EmployeeViewModel
    import com.example.timetally.Adapter.EmployeeAdapter
    import com.example.timetally.Adapter.PresenceAdapter
    import com.example.timetally.R

    class EmployeeListFragment : Fragment() {

        private lateinit var employeeViewModel: EmployeeViewModel
        private lateinit var employeeAdapter: EmployeeAdapter
        private lateinit var presenceAdapter: PresenceAdapter

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_employee_list, container, false)

            val recyclerViewEmployee = view.findViewById<RecyclerView>(R.id.employeeRecyclerView)
            val recyclerViewEmployeePresence = view.findViewById<RecyclerView>(R.id.recyclerViewEmployeePresence)

            employeeAdapter = EmployeeAdapter { employee ->
                presenceAdapter.addEmployee(employee)
            }
            presenceAdapter = PresenceAdapter()

            recyclerViewEmployee.adapter = employeeAdapter
            recyclerViewEmployee.layoutManager = LinearLayoutManager(context)

            recyclerViewEmployeePresence.adapter = presenceAdapter
            recyclerViewEmployeePresence.layoutManager = LinearLayoutManager(context)

            employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
            employeeViewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
                employees?.let { employeeAdapter.setEmployees(it) }
            })

            return view
        }
    }
*/
/*

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
    import com.example.ViewModel.EmployeeViewModel
    import com.example.timetally.Adapter.EmployeeAdapter
    import com.example.timetally.Adapter.PresenceAdapter
    import com.example.timetally.R

    class EmployeeListFragment : Fragment() {

        private lateinit var employeeViewModel: EmployeeViewModel
        private lateinit var employeeAdapter: EmployeeAdapter
        private lateinit var presenceAdapter: PresenceAdapter

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_employee_list, container, false)

            val recyclerViewEmployee = view.findViewById<RecyclerView>(R.id.employeeRecyclerView)
            val recyclerViewEmployeePresence = view.findViewById<RecyclerView>(R.id.recyclerViewEmployeePresence)

            employeeAdapter = EmployeeAdapter { employee ->
                employeeViewModel.addPresentEmployee(employee)
            }
            presenceAdapter = PresenceAdapter()

            recyclerViewEmployee.adapter = employeeAdapter
            recyclerViewEmployee.layoutManager = LinearLayoutManager(context)

            recyclerViewEmployeePresence.adapter = presenceAdapter
            recyclerViewEmployeePresence.layoutManager = LinearLayoutManager(context)

            employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)
            employeeViewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
                employees?.let { employeeAdapter.setEmployees(it) }
            })

            employeeViewModel.presentEmployees.observe(viewLifecycleOwner, Observer { presentEmployees ->
                presentEmployees?.let { presenceAdapter.setEmployees(it) }
            })

            return view
        }
    }
*/
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import com.example.ViewModel.EmployeeViewModel
    import com.example.timetally.Adapter.EmployeeAdapter
    import com.example.timetally.Adapter.PresenceAdapter
    import com.example.timetally.R

    class EmployeeListFragment : Fragment() {

        private lateinit var employeeViewModel: EmployeeViewModel
        private lateinit var employeeAdapter: EmployeeAdapter
        private lateinit var presenceAdapter: PresenceAdapter

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_employee_list, container, false)

            val recyclerViewEmployee = view.findViewById<RecyclerView>(R.id.employeeRecyclerView)
            val recyclerViewEmployeePresence = view.findViewById<RecyclerView>(R.id.recyclerViewEmployeePresence)

            employeeViewModel = ViewModelProvider(this).get(EmployeeViewModel::class.java)

            employeeAdapter = EmployeeAdapter(employeeViewModel.getEmployeeDao()) { employee ->
                employeeViewModel.addPresentEmployee(employee)
            }
            presenceAdapter = PresenceAdapter()

            recyclerViewEmployee.adapter = employeeAdapter
            recyclerViewEmployee.layoutManager = LinearLayoutManager(context)

            recyclerViewEmployeePresence.adapter = presenceAdapter
            recyclerViewEmployeePresence.layoutManager = LinearLayoutManager(context)

            employeeViewModel.allEmployees.observe(viewLifecycleOwner, Observer { employees ->
                employees?.let { employeeAdapter.setEmployees(it) }
            })

            employeeViewModel.presentEmployees.observe(viewLifecycleOwner, Observer { presentEmployees ->
                presentEmployees?.let { presenceAdapter.setEmployees(it) }
            })
            return view
        }
    }
