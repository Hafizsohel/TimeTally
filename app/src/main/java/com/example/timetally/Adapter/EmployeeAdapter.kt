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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "EmployeeAdapter"
class EmployeeAdapter(
    private val employeeDao: EmployeeDao,
    private val onEmployeePresentChecked: (Employee) -> Unit)
    : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {
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

        // Avoid triggering the listener when setting the checkbox state
        holder.checkPresent.setOnCheckedChangeListener(null)
        holder.checkPresent.isChecked = currentEmployee.isPresent

        holder.checkPresent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != currentEmployee.isPresent) {
                currentEmployee.isPresent = isChecked
                onEmployeePresentChecked(currentEmployee)

                if (isChecked) {
                    removeEmployee(holder.adapterPosition)
                }
                Log.d(TAG, "onBindViewHolder: Employee ${currentEmployee.name} is present: $isChecked")
            }
        }
    }
    override fun getItemCount(): Int = employees.size

    fun setEmployees(employees: List<Employee>) {
        this.employees = employees.toMutableList()
        notifyDataSetChanged()
    }

    private fun removeEmployee(position: Int) {
        if (position != -1) {
            employees.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, employees.size)
        }
    }

}
