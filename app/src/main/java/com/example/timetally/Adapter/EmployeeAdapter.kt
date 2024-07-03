package com.example.timetally.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.DAO.EmployeeDao
import com.example.timetally.Data.Employee
import com.example.timetally.R

/*
package com.example.timetally.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timetally.Data.Employee
import com.example.timetally.R

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employees = emptyList<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
        val checkPresent: CheckBox = itemView.findViewById(R.id.checkPresent)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.employee_list, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name

    }

    override fun getItemCount(): Int {
        return employees.size
    }
    fun setEmployees(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }
}
*/

/*

private const val TAG = "EmployeeAdapter"
class EmployeeAdapter(
    private val onEmployeePresentChecked: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employees = mutableListOf<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
        val checkPresent: CheckBox = itemView.findViewById(R.id.checkPresent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name
        holder.checkPresent.isChecked = currentEmployee.isPresent

        holder.checkPresent.setOnCheckedChangeListener(null) // Remove previous listener


        holder.checkPresent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onEmployeePresentChecked(currentEmployee)
                removeEmployee(currentEmployee)
                Log.d(TAG, "onBindViewHolder: " + currentEmployee)
            }
        }
    }

    override fun getItemCount(): Int = employees.size

    private fun removeEmployee(employee: Employee) {
        val index = employees.indexOf(employee)
        if (index != -1) {
            employees.removeAt(index)
            notifyItemRemoved(index)
            notifyItemRangeChanged(index, employees.size) // Update remaining items' serial numbers
        }
    }

    fun setEmployees(employees: List<Employee>) {
        this.employees = employees.toMutableList()
        notifyDataSetChanged()
    }
}
*/

// File: com.example.timetally.Adapter.EmployeeAdapter.kt

// File: com.example.timetally.Adapter.EmployeeAdapter.kt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmployeeAdapter(
    private val employeeDao: EmployeeDao,
    private val onEmployeePresentChecked: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employees = emptyList<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val employeeName: TextView = itemView.findViewById(R.id.employeeName)
        val serialNoText: TextView = itemView.findViewById(R.id.serialNoText)
        val checkPresent: CheckBox = itemView.findViewById(R.id.checkPresent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.serialNoText.text = "${position + 1}."
        holder.employeeName.text = currentEmployee.name
        holder.checkPresent.isChecked = currentEmployee.isPresent

        holder.checkPresent.setOnClickListener(null)
        holder.checkPresent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != currentEmployee.isPresent) {
                currentEmployee.isPresent = isChecked
                updateEmployeePresence(currentEmployee)
                onEmployeePresentChecked(currentEmployee)
                Log.d(TAG, "onBindViewHolder: Employee ${currentEmployee.name} is present: $isChecked")
            }
        }
    }

    override fun getItemCount(): Int = employees.size

    fun setEmployees(employees: List<Employee>) {
        this.employees = employees
        notifyDataSetChanged()
    }

    private fun updateEmployeePresence(employee: Employee) {
        GlobalScope.launch(Dispatchers.IO) {
            employeeDao.update(employee)
        }
    }
    companion object {
        private const val TAG = "EmployeeAdapter"
    }
}
